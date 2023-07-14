package com.mvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mvc.dto.GradeDTO;
import com.mvc.mapper.GradeMapper;

@Service
public class GradeService {
	private GradeMapper mapper;

	public GradeService(GradeMapper mapper) {
		this.mapper = mapper;
	}

	public List<GradeDTO> selectAllGrade() {
		return mapper.selectAllGrade();
	}
	
	
}









