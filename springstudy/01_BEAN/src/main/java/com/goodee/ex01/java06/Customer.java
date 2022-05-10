package com.goodee.ex01.java06;

import java.util.Map;

public class Customer {

	// fields
	private String name;              // 고객명
	private Map<String, String> info; // 고객정보
	private BankAccount bankAccount;  // 계좌
	
	// default constructor
	// setter injection 사용하기 위함
	public Customer() {
	}
	// constructor using fields
	// constructor injection 사용하기 위함
	public Customer(String name, Map<String, String> info, BankAccount bankAccount) {
		super();
		this.name = name;
		this.info = info;
		this.bankAccount = bankAccount;
	}
	
	// info() 메소드
	public void info() {
		System.out.println("name : " + name);
		for(Map.Entry<String, String> entry : info.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		System.out.println("accNo : " + bankAccount.getAccNo());
		System.out.println("balance : " + bankAccount.getBalance());
	}
	
	// getter/setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getInfo() {
		return info;
	}
	public void setInfo(Map<String, String> info) {
		this.info = info;
	}
	public BankAccount getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	
}
