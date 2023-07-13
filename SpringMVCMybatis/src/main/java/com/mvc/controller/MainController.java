package com.mvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.dto.MemberDTO;
import com.mvc.service.MemberService;

@Controller
public class MainController {
	private MemberService service;

	public MainController(MemberService service) {
		this.service = service;
	}
	
	@RequestMapping("/")
	public String main() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(String id, String passwd, HttpSession session) {
		MemberDTO dto = service.login(id,passwd);
		if(dto != null) {
			session.setAttribute("user", dto);
			return "redirect:/main";
		}
		return "redirect:/";
	}
}














