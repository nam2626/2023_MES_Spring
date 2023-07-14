package com.mvc.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.dto.GradeDTO;
import com.mvc.dto.MemberDTO;
import com.mvc.service.GradeService;
import com.mvc.service.MemberService;

@Controller
public class MainController {
	private MemberService memberService;
	private GradeService gradeService;
	
	public MainController(MemberService memberService, GradeService gradeService) {
		this.memberService = memberService;
		this.gradeService = gradeService;
	}
	
	@RequestMapping("/")
	public String main() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(String id, String passwd, HttpSession session) {
		MemberDTO dto = memberService.login(id,passwd);
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
		List<MemberDTO> list = memberService.selectAllMember();
		//2. request 영역에 읽어온 전체회원 정보를 저장
		view.addObject("list", list);
		view.setViewName("main");
		return view;
	}
	
	//1. member_register.jsp로 이동하는 메서드 작성
	@RequestMapping("/member/register/view")
	public String registerView() {
		return "member_register";
	}
	
	//2. 등록할 회원정보를 받아서 DB에 등록하는 메서드 작성
	@RequestMapping("/member/register/action")
	public String register(MemberDTO dto) {
		//사용자가 보낸 데이터를 받아서 service에 전달  
		int result = memberService.insertMember(dto);
		return "redirect:/main";
	}
	
	@RequestMapping("/member/detail/{id}")
	public ModelAndView detail(@PathVariable String id, ModelAndView view) {
		MemberDTO dto = memberService.selectMember(id);
		view.addObject("member", dto);
		view.setViewName("member_update_view");
		return view;
	}
	
	@RequestMapping("/member/update")
	public String update(MemberDTO dto) {
		memberService.updateMember(dto);
		return "redirect:/main";
	}
	
	@RequestMapping("/member/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable String id){
		int result = memberService.deleteMember(id);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("count", result);
		if(result == 0)
			map.put("message", "데이터 삭제 실패");
		else
			map.put("message", "데이터 삭제 성공");
		
		return new ResponseEntity(map,HttpStatus.OK);
	}
	
	@RequestMapping("/member/search")
	public ResponseEntity<String> search(String kind, String search){
		List<MemberDTO> list = memberService.searchMember(kind,search);
		return new ResponseEntity(list,HttpStatus.OK);
	}
	
	@RequestMapping("/grade")
	public ModelAndView gradeMain(ModelAndView view) {
		//전체 회원 등급을 가져옴
		List<GradeDTO> list = gradeService.selectAllGrade();
		view.addObject("list", list);
		view.setViewName("grade_manage");
		return view;
	}
	
	@PostMapping("/grade/append")
	public ResponseEntity<String> registerGrade(int grade_no, String grade_name){
		GradeDTO dto = new GradeDTO(grade_no, grade_name);
		int result = gradeService.insertGrade(dto);
		List<GradeDTO> list = gradeService.selectAllGrade();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("list", list);
		return new ResponseEntity(map,HttpStatus.OK);
	}
	
	@RequestMapping("/grade/search")
	public ResponseEntity<String> searchGrade(String search){
		List<GradeDTO> list = gradeService.searchGrade(search);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return new ResponseEntity(map,HttpStatus.OK);
	}
	
	@RequestMapping("/grade/delete")
	public ResponseEntity<String> deleteGrade(int grade_no) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int result = gradeService.deleteGrade(grade_no);
		map.put("result", result);
		return new ResponseEntity(map,HttpStatus.OK);
	}
	@PostMapping("/grade/update")
	public ResponseEntity<String> updateGrade(int grade_no, String grade_name){
		GradeDTO dto = new GradeDTO(grade_no, grade_name);
		int result = gradeService.updateGrade(dto);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return new ResponseEntity(map,HttpStatus.OK);
	}
}














