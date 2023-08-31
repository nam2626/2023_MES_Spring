package com.file;

public class BoardDTO {
	private String title;
	private String writer;
	private String content;

	public BoardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BoardDTO(String title, String writer, String content) {
		super();
		this.title = title;
		this.writer = writer;
		this.content = content;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public String toString() {
		return "BoardDTO [title=" + title + ", writer=" + writer + ", content=" + content + "]";
	}

}
