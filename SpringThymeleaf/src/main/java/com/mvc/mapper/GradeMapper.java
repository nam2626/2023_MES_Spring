package com.mvc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mvc.dto.GradeDTO;

@Mapper
public interface GradeMapper {

	List<GradeDTO> selectAllGrade();
	int insertGrade(GradeDTO dto);

}
