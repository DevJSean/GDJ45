package com.goodee.ex05.domain;

public class MemberDTO {

	//field
	private String id;
	private String pw;
	
	// default constructor
	public MemberDTO() {
		
	}
	
	// constructor using fields
	public MemberDTO(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}

	// getter/setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	
	
	
	
	
	
}
