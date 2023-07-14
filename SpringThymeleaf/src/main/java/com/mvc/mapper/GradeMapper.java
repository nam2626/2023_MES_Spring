package com.mvc.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mvc.dto.GradeDTO;

@Mapper
public interface GradeMapper {

	List<GradeDTO> selectAllGrade();
	int insertGrade(GradeDTO dto);
	List<GradeDTO> selectGrade(String search);
	int deleteGrade(int grade_no);
	int updateGrade(GradeDTO dto);

}
