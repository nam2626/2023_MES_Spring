package com.mvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping("/main")
	public ModelAndView memberList(ModelAndView view) {
		//1. 전체 회원 정보를 DB에서 조회해서 가져옴
		//     - List로 받음
		
		//2. request 영역에 읽어온 전체회원 정보를 저장
		return view;
	}
}














