package com.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String main() {
		return "login";
	}
	
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request) {
		//아이디 비번 받아서 세션에 저장 id, pass
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		session.setAttribute("pass", pass);
		
		return "login_result";
	}
	
}











