package student_registration_with_spring_n_mybatis.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckUser {
	public boolean isEmpty(HttpSession session,HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0"); // Proxies.

		if(session.getAttribute("loginUser")==null) {
			return true;
		}
		return false;
	}
}
