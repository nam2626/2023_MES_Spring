package com.mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public int insertGrade(GradeDTO dto) {
		return mapper.insertGrade(dto);
	}

	public List<GradeDTO> searchGrade(String search) {
		return mapper.selectGrade(search);
	}

	public int deleteGrade(int grade_no) {
		return mapper.deleteGrade(grade_no);
	}

	public int updateGrade(GradeDTO dto) {
		return mapper.updateGrade(dto);
	}
	
	
}









