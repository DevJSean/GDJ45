package com.goodee.ex04.domain;

public class Member {
	
	// field
	private String name;
	private int age;
	
	// default constructor
	public Member() {
	}

	// constructor using fields
	public Member(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	// getter/setter
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
}
