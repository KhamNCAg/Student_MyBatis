package student_registration_with_spring_n_mybatis.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import student_registration_with_spring_n_mybatis.mapper.UserMapper;
import student_registration_with_spring_n_mybatis.model.User;

@Controller
public class LogController {

	@Autowired
	UserMapper umapper;
	
	@GetMapping("/")
	public ModelAndView setupLogin(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0"); // Proxies.

		return new ModelAndView("login", "usr", new User());
	}
	
	@PostMapping("/login")
	public ModelAndView Login(@ModelAttribute("usr") User bean, ModelMap model, HttpSession session, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0"); // Proxies.

			User temp = umapper.Login(bean.getId(), bean.getPassword());
			if (temp!=null) {
				if(bean.getId().equals(temp.getId())&&bean.getPassword().equals(temp.getPassword())) {
					session.setAttribute("loginUser", new User(temp.getId(), temp.getName()));
					return new ModelAndView("redirect:/menu");	
				}else {
					model.addAttribute("err", "Invalid userID and password!");
				}
				
			} else {
				model.addAttribute("err", "Invalid userID and password!");
			}
		return new ModelAndView("login","usr", bean);
	}
	
	@GetMapping("/menu")
	public String menu(HttpSession session, HttpServletResponse response) {
		if (new CheckUser().isEmpty(session,response)) {return "redirect:/";}
		return "menu";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {	
		session.removeAttribute("loginUser");
		session.invalidate();
		return "redirect:/";
	}
	
}
