package com.file;

import org.apache.ibatis.type.Alias;

@Alias("file")
public class FileDTO {
	private int fno;
	private String fpath;

	public FileDTO() {
	}

	public int getFno() {
		return fno;
	}

	public void setFno(int fno) {
		this.fno = fno;
	}

	public String getFpath() {
		return fpath;
	}

	public void setFpath(String fpath) {
		this.fpath = fpath;
	}

	@Override
	public String toString() {
		return "FileDTO [fno=" + fno + ", fpath=" + fpath + "]";
	}

}
