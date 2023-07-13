package com.mvc.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mvc.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	MemberDTO login(Map<String, Object> map);
	List<MemberDTO> selectAllMember();
	int insertMember(MemberDTO dto);
	MemberDTO selectMember(String id);
	int updateMember(MemberDTO dto);

}









