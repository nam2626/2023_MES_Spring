package com.mvc;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	private RegisterService service;
	
	public MainController(RegisterService service) {
		this.service = service;
	}

	@RequestMapping("/")
	public String main() {
		return "login";
	}
	
	//jsp에서 컨트롤러로 처리했던 방식
//	@RequestMapping("/login.do")
//	public String login(HttpServletRequest request) {
//		//아이디 비번 받아서 세션에 저장 id, pass
//		String id = request.getParameter("id");
//		String pass = request.getParameter("pass");
//		
//		HttpSession session = request.getSession();
//		session.setAttribute("id", id);
//		session.setAttribute("pass", pass);
//		
//		return "login_result";
//	}
	//세션 객체를 역주입으로 받아옴
//	@RequestMapping("/login.do")
//	public String login(HttpServletRequest request,HttpSession session) {
//		//아이디 비번 받아서 세션에 저장 id, pass
//		String id = request.getParameter("id");
//		String pass = request.getParameter("pass");
//		
//		session.setAttribute("id", id);
//		session.setAttribute("pass", pass);
//		
//		return "login_result";
//	}
	//parameter 받아오는 방법
//	@RequestMapping("/login.do")
//	public String login(@RequestParam(name="id",defaultValue="user") String id,
//			@RequestParam(name="pass",defaultValue="123456") String pass,
//			HttpSession session) {
//		
//		session.setAttribute("id", id);
//		session.setAttribute("pass", pass);
//		
//		return "login_result";
//	}
	@RequestMapping("/login.do")
	public String login(String id,String pass, HttpSession session) {
		
		session.setAttribute("id", id);
		session.setAttribute("pass", pass);
		
		return "login_result";
	}
	
	//registerView.do
	//register.jsp 페이지로 이동
	@RequestMapping("/registerView.do")
	public String registerView() {
		return "register";
	}
	//register.do
	//회원 가입정보를 받아서 가입 정보를 저장할 RegisterDTO 클래스 작성을 해서
	//request 영역에 저장, 이동할 페이지는 register_result.jsp로 이동
	//request영역에 객체를 저장할 때는 이름을 dto로 저장
//	@RequestMapping("register.do")
//	public String register(String id, String pass, String name, int age, 
//			HttpServletRequest request) {
//		RegisterDTO dto = new RegisterDTO(id, pass, name, age);
//		request.setAttribute("dto", dto);
//		return "register_result";
//	}
	@RequestMapping("register.do")
	public ModelAndView register(RegisterDTO dto, ModelAndView model) {
		//request 영역에 데이터 저장
		model.addObject("dto", dto);
		//이동할 페이지 설정
		model.setViewName("register_result");
		service.testService();
		return model;
	}
	
}











