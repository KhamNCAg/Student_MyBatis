package student_registration_with_spring_n_mybatis.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import student_registration_with_spring_n_mybatis.model.User;
import student_registration_with_spring_n_mybatis.persistent.dao.UserDao;
import student_registration_with_spring_n_mybatis.persistent.dto.UserRequestDTO;
import student_registration_with_spring_n_mybatis.persistent.dto.UserResponseDTO;

@Controller
public class UserController {
	
	@Autowired
	private UserDao dao;
	private String getID() {
		DecimalFormat temp = new DecimalFormat("USR000");
    	String id = dao.getLastId();
    	if(id==null) {
    		return temp.format(1);
    	}else{
    		String[] ary = id.split("R");
    		double t = Double.valueOf(ary[1]);
    		return temp.format(++t);
		}    	
	}	
	
	@GetMapping("/setup_search_user")
	public ModelAndView manageUser(ModelMap model, HttpSession session, HttpServletResponse response, @ModelAttribute("sus")String sus, @ModelAttribute("err")String err) {
		if (new CheckUser().isEmpty(session,response)) {return new ModelAndView("redirect:/");}
		model.addAttribute("sus",sus);
		model.addAttribute("err",err);
		model.addAttribute("urs", dao.getUsers());
		return new ModelAndView("user_search", "user", new User());
	}
	
	@PostMapping("/search_user")
	public ModelAndView searchUser(@ModelAttribute("user") User bean, ModelMap model, HttpSession session, HttpServletResponse response) {
		if (new CheckUser().isEmpty(session,response)) {return new ModelAndView("redirect:/");}
		String id = bean.getId();
		String name = bean.getName();
		if (id.equals("") && name.equals("")) {
			model.addAttribute("urs", dao.getUsers());
		} else {
			model.addAttribute("urs", dao.searchForTable(id,name));
		}
		return new ModelAndView("user_search", "user", bean);
	}
	
	@GetMapping("/setup_reg_user")
	public ModelAndView setupAddUser(HttpSession session, HttpServletResponse response, @ModelAttribute("sus")String sus, ModelMap model) {
		if (new CheckUser().isEmpty(session,response)) {return new ModelAndView("redirect:/");}
		model.addAttribute("sus",sus);
		return new ModelAndView("user_reg", "user", new User());
	}
	
	@PostMapping("/reg_user")
	public ModelAndView addUser(@ModelAttribute("usr") User bean, ModelMap model, HttpSession session, HttpServletResponse response) {
		if (new CheckUser().isEmpty(session,response)) {return new ModelAndView("redirect:/");}
		String id = getID();
		String name = bean.getName();
		String email = bean.getEmail();
		String pass = bean.getPassword();
		String cpass = bean.getCpassword();
		String role = bean.getRole();
		if(name!=null&&!name.isBlank()&&email!=null&&!email.isBlank()&&pass!=null&&!pass.isBlank()&&cpass!=null&&!cpass.isBlank()&&role!=null&&!role.isBlank()) {
		if(pass.equals(cpass)) {
			if(dao.emailExists(id,email)){
				model.addAttribute("err", "Email already exists.");
				return new ModelAndView("user_reg", "user", bean);
			}
			UserRequestDTO user = new UserRequestDTO(id,name,email,pass,role);
			if(0<dao.insertData(user)) {
				model.addAttribute("sus", "Successfully Created.");
				return new ModelAndView("redirect:/setup_reg_user");
			}
		}else {
			model.addAttribute("err", "Passwords do not match!");
		}
		}else{
			model.addAttribute("err", "Please enter data correctly.");
		}		
		return new ModelAndView("user_reg", "user", bean);
	}
	
	@GetMapping("/setup_update_user/{id}")
	public ModelAndView setupUpdateUser(@PathVariable String id, HttpSession session, HttpServletResponse response, @ModelAttribute("sus")String sus, ModelMap model) {
		if (new CheckUser().isEmpty(session,response)){return new ModelAndView("redirect:/");}	
		model.addAttribute("sus",sus);
		UserResponseDTO temp = dao.selectOne(id);
		User user = new User();
		user.setId(temp.getId());
		user.setEmail(temp.getEmail());
		user.setName(temp.getName());
		user.setPassword(temp.getPassword());
		user.setCpassword(temp.getPassword());
		user.setRole(temp.getRole());
		return new ModelAndView("user_update","user",user);
	}
	
	@PostMapping("update_user")
	public ModelAndView updateUser(@ModelAttribute("user") User bean, ModelMap model, HttpSession session, HttpServletResponse response) {
		if (new CheckUser().isEmpty(session,response)) {return new ModelAndView("redirect:/");}
		String id = bean.getId();
		String email = bean.getEmail();
		String name = bean.getName();
		String pass = bean.getPassword();
		String cpass = bean.getCpassword();
		String role = bean.getRole();
		if(name!=null&&!name.isBlank()&&email!=null&&!email.isBlank()&&pass!=null&&!pass.isBlank()&&cpass!=null&&!cpass.isBlank()&&role!=null&&!role.isBlank()) {
			if(pass.equals(cpass)) {
				if(dao.emailExists(id,email)){
					model.addAttribute("err", "Email already exists.");
					return new ModelAndView("user_update", "user", bean);
				}
				UserRequestDTO user = new UserRequestDTO(id,name,email,pass,role);
				if(0<dao.updateData(user)) {
					model.addAttribute("sus", "Successfully Updated.");
					return new ModelAndView("redirect:/setup_search_user");
				}else {
					model.addAttribute("err", "Update failed!!");
				}
			}else {
				model.addAttribute("err", "Passwords do not match!");
			}
			}else{
				model.addAttribute("err", "Please enter data correctly.");
			}		
			return new ModelAndView("user_update", "user", bean);
	}
	
	@GetMapping("/delete_user/{del_id}")
	public String deleteUser(@PathVariable String del_id,ModelMap model, HttpSession session, HttpServletResponse response) {
		if (new CheckUser().isEmpty(session,response)) {return "redirect:/";}
		User usr = (User) session.getAttribute("loginUser");
		if(del_id.equals(usr.getId())) {
			System.out.println(del_id+"="+usr.getId());
			model.addAttribute("err","User can't be deleted. It's currently active!");
		}else {
		if(dao.deleteData(del_id)==0) {
			model.addAttribute("err","Delete Failed!");
		}else {
			model.addAttribute("sus","Successfully Deleted!");
		}
		}
		return "redirect:/setup_search_user";		
	}
	
	@ModelAttribute("roleList") 
	public Map<String, String> getRoleList()
	{
	Map<String, String> roleList = new HashMap<String, String>();
	roleList.put("Admin", "Admin");
	roleList.put("User", "User");
	return roleList;
	}
	
}
