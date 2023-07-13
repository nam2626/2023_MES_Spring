package com.mvc.service;

import org.springframework.stereotype.Service;

import com.mvc.mapper.MemberMapper;

@Service
public class MemberService {
	private MemberMapper mapper;

	public MemberService(MemberMapper mapper) {
		this.mapper = mapper;
	}
	
	
}
