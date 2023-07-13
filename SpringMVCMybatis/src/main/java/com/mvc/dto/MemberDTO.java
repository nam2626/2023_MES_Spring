package com.mvc.dto;

import org.apache.ibatis.type.Alias;

@Alias("member")
public class MemberDTO {
	private String memberId;
	private String passwd;
	private String name;
	private int age;
	private char gender;
	
	public MemberDTO() {
	}

	public MemberDTO(String memberId, String passwd, String name, int age, char gender) {
		this.memberId = memberId;
		this.passwd = passwd;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "MemberDTO [memberId=" + memberId + ", passwd=" + passwd + ", name=" + name + ", age=" + age
				+ ", gender=" + gender + "]";
	}
	
}
