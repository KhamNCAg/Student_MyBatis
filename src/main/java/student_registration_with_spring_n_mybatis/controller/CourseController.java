package student_registration_with_spring_n_mybatis.controller;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import student_registration_with_spring_n_mybatis.model.Course;
import student_registration_with_spring_n_mybatis.persistent.dao.CourseDao;
import student_registration_with_spring_n_mybatis.persistent.dto.CourseRequestDTO;



@Controller
public class CourseController {
	@Autowired
	private CourseDao dao;
	private String getID() {
		DecimalFormat temp = new DecimalFormat("COU000");
    	String id = dao.getLastId();
    	if(id==null) {
    		return temp.format(1);
    	}else{
    		String[] ary = id.split("U");
    		double t = Double.valueOf(ary[1]);
    		return temp.format(++t);
		}    	
	}
	
	@GetMapping("/course")
	public ModelAndView setupAddClass(HttpSession session, HttpServletResponse response, ModelMap model, @ModelAttribute("err")String err, @ModelAttribute("sus")String sus) {
		if (new CheckUser().isEmpty(session, response)) {return new ModelAndView("redirect:/");}
		model.addAttribute("err",err);
		model.addAttribute("sus",sus);
		Course cur = new  Course();
		cur.setId(getID());
		return new ModelAndView("class_reg","cls",cur);		
	}
	
	@PostMapping("/reg_course")
	public String regClass(@ModelAttribute("cls")Course bean, ModelMap model, HttpSession session, HttpServletResponse response) {
		if (new CheckUser().isEmpty(session,response)) {return "redirect:/";}
		if(bean.getName().isBlank()) {
			model.addAttribute("err","Enter Course Name!!!");
			return "redirect:/course";
		}
		CourseRequestDTO dto = new CourseRequestDTO();
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		int rs = dao.insertData(dto);
		if(rs==0) {
			model.addAttribute("err","Registration Failed");
			return "redirect:/course";
		}
		model.addAttribute("sus","Successfully Registered.");
		return "redirect:/course";
	
	}
	
}
