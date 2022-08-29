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

import student_registration_with_spring_n_mybatis.model.Student;
import student_registration_with_spring_n_mybatis.persistent.dao.CourseDao;
import student_registration_with_spring_n_mybatis.persistent.dao.CoursesOfStudentDao;
import student_registration_with_spring_n_mybatis.persistent.dao.StudentDao;
import student_registration_with_spring_n_mybatis.persistent.dto.StudentRequestDTO;

@Controller
public class StudentController {
	@Autowired
	private StudentDao dao;
	@Autowired
	private CourseDao cdao;
	@Autowired
	private CoursesOfStudentDao csdao;
	
	private String getID() {
		DecimalFormat temp = new DecimalFormat("STU000");
    	String id = dao.getLastId();
    	if(id==null) {
    		return temp.format(1);
    	}else{
    		String[] ary = id.split("U");
    		double t = Double.valueOf(ary[1]);
    		return temp.format(++t);
    	}    	
	}

	@GetMapping("/setup_reg_student")
	public ModelAndView setupRegStudent(ModelMap model, HttpSession session, HttpServletResponse response, @ModelAttribute("sus")String sus) {
		if (new CheckUser().isEmpty(session, response)) {return new ModelAndView("redirect:/");}
		model.addAttribute("sus",sus);
		Student stu = new Student();
		stu.setId(getID());
		model.addAttribute("curs",cdao.selectAllNames());
		return new ModelAndView("student_reg","stu",stu);		
	}
	
	@PostMapping("/reg_student")
	public String regStudent(@ModelAttribute("stu") Student bean, ModelMap model, HttpSession session, HttpServletResponse response) {
		if (new CheckUser().isEmpty(session,response)) {return "redirect:/";}
		try{
		model.addAttribute("curs",cdao.selectAllNames());
		if(bean.getId().isBlank() || bean.getName().isBlank() || bean.getDob().isBlank() || bean.getGender().isBlank() || bean.getPhone().isBlank() || bean.getEducation().isBlank() || bean.getCourses().length<0 ) {
			model.addAttribute("err","Fields cannot be blank!");
			model.addAttribute("stu",bean);
			return "student_reg";
		}else {
		StudentRequestDTO dto = new StudentRequestDTO();
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		dto.setDob(bean.getDob());
		dto.setGender(bean.getGender());
		dto.setPhone(bean.getPhone());
		dto.setEducation(bean.getEducation());
		if(0<csdao.insertDataByCourseNames(dto.getId(), bean.getCourses())) {
			if(0<dao.insertData(dto)) {
				model.addAttribute("sus","Successfully Created!");
				return "redirect:/setup_reg_student";
			}
				
		}
			model.addAttribute("err","Insert Failed");
			model.addAttribute("stu",bean);
			return "student_reg";		
		}
		}catch (NullPointerException e) {
			model.addAttribute("err","Fields cannot be blank!");
			model.addAttribute("stu",bean);
			return "student_reg";
		}
	}
	
	@GetMapping("/setup_search_student")
	public ModelAndView setupSearchStudent(ModelMap model, HttpSession session, HttpServletResponse response, @ModelAttribute("err")String err, @ModelAttribute("sus")String sus) {
		if (new CheckUser().isEmpty(session,response)) {return new ModelAndView("redirect:/");}
		model.addAttribute("err",err);
		model.addAttribute("sus",sus);
		model.addAttribute("students", dao.selectAllForTable());
		return new ModelAndView("student_search", "stu", new Student());
	}
	
	@PostMapping("/search_student")
	public ModelAndView searchStudent(@ModelAttribute("stu") Student bean, ModelMap model, HttpSession session, HttpServletResponse response) {
		if (new CheckUser().isEmpty(session,response)) {return new ModelAndView("redirect:/");}
		String id = bean.getId();
		String name = bean.getName();
		String course = bean.getCourse2find();
			if (id.equals("") && name.equals("") && course.equals("")) {
				model.addAttribute("students", dao.selectAllForTable());
			} else {
				model.addAttribute("students", dao.searchForTable(id, name, course));
			}
		return new ModelAndView("student_search", "stu", bean);	
	}
	
	@GetMapping("/student_detail/{id}")
	public ModelAndView setupUpdateStudent(@PathVariable String id, HttpSession session, HttpServletResponse response, ModelMap model) {
		if (new CheckUser().isEmpty(session,response)) {return new ModelAndView("redirect:/");}
		model.addAttribute("curs",cdao.selectAllNames());
		return new ModelAndView("student_update", "stu", dao.selectOne(id));
	}
	
	@PostMapping("/update_student")
	public ModelAndView updateStudent(@ModelAttribute("stu")Student bean, ModelMap model, HttpSession session, HttpServletResponse response) {
		if (new CheckUser().isEmpty(session,response)) {return new ModelAndView("redirect:/");}

		if(bean.getId().isBlank() || bean.getName().isBlank() || bean.getDob().isBlank() || bean.getGender().isBlank() || bean.getPhone().isBlank() || bean.getEducation().isBlank() || bean.getCourses().length<0 || bean.getCourses() == null) {
			model.addAttribute("err","Fields cannot be blank!");
			return new ModelAndView("stu_update","stu",bean);
		}else {
			StudentRequestDTO dto = new StudentRequestDTO();
			dto.setId(bean.getId());
			dto.setName(bean.getName());
			dto.setDob(bean.getDob());
			dto.setGender(bean.getGender());
			dto.setPhone(bean.getPhone());
			dto.setEducation(bean.getEducation());;
			if(0<csdao.deleteCoursesByStuID(dto.getId())) {
				if(0<csdao.insertDataByCourseNames(dto.getId(), bean.getCourses())) {
					if(0<dao.updateData(dto)) {
						model.addAttribute("sus", "Successfully Updated.");
						return new ModelAndView("redirect:/setup_search_student");
					}
					
				}
			}
				model.addAttribute("err","Update Failed");
				return new ModelAndView("redirect:/student_detail/{"+bean.getId()+"}");
			}		
	}
	
	@GetMapping("/delete_student/{id}")
	public String deleteStudent(@PathVariable String id,ModelMap model, HttpSession session, HttpServletResponse response) {
		if (new CheckUser().isEmpty(session,response)) {return "redirect:/";}
		int rs = dao.deleteData(id);
		if(rs==0) {
			model.addAttribute("err","Delete Failed!");
		}else {
			if(0<csdao.deleteCoursesByStuID(id)) {
			model.addAttribute("sus","Successfully Deleted!");
			}else {model.addAttribute("err","Delete Failed!");}
		}
		return "redirect:/setup_search_student";
	}
	
	@ModelAttribute("EduList") 
	public Map<String, String> getEduList()
	{
	Map<String, String> eduList = new HashMap<String, String>();
	eduList.put("Bachelor of Information Technology", "Bachelor of Information Technology");
	eduList.put("Diploma in IT", "Diploma in IT");
	eduList.put("Bachelor of Computer Science", "Bachelor of Computer Science");
	return eduList;
	}
}
