package com.mvc.dto;

import org.apache.ibatis.type.Alias;

@Alias("grade")
public class GradeDTO {
	private int gradeNo;
	private String gradeName;

	public GradeDTO(int gradeNo, String gradeName) {
		this.gradeNo = gradeNo;
		this.gradeName = gradeName;
	}

	public GradeDTO() {
	}

	public int getGradeNo() {
		return gradeNo;
	}

	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	@Override
	public String toString() {
		return "GradeDTO [gradeNo=" + gradeNo + ", gradeName=" + gradeName + "]";
	}

}
