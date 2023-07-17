package com.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.student.dto.StudentDTO;

@Mapper
public interface StudentMapper {

	List<StudentDTO> selectAllStudent();

	List<StudentDTO> selectStudent(Map<String, Object> map);

}
