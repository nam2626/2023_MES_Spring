package com.mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mvc.dto.MemberDTO;
import com.mvc.mapper.MemberMapper;

@Service
public class MemberService {
	private MemberMapper mapper;

	public MemberService(MemberMapper mapper) {
		this.mapper = mapper;
	}

	public MemberDTO login(String id, String passwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("passwd", passwd);
		
		return mapper.login(map);
	}

	public List<MemberDTO> selectAllMember() {
		return mapper.selectAllMember();
	}
	
	
}











