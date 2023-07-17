package com.student;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.student.dto.StudentDTO;
import com.student.service.StudentService;

@Controller
public class MainController {
	private StudentService service;

	public MainController(StudentService service) {
		this.service = service;
	}
	
	@RequestMapping("/")
	public ModelAndView main(ModelAndView view) {
		List<StudentDTO> list = service.selectAllStudent();

		view.setViewName("student_search");
		view.addObject("list", list);
		
		return view;
	}
	
	@RequestMapping("/search.do")
	public ResponseEntity<String> searchStudent(String kind, String search){
		List<StudentDTO> list = service.selectStudent(kind, search);
		return new ResponseEntity(list,HttpStatus.OK);
	}
}









