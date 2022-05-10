package com.goodee.ex01.java06;

public class BankAccount {

	// field
	private String accNo; // 계좌번호
	private Long balance; // 잔액
	
	// default constructor
	// setter injection 이용하기 위함
	public BankAccount() {
	}
	// constructor using fields
	// constructor injection 하기 위함
	public BankAccount(String accNo, Long balance) {
		super();
		this.accNo = accNo;
		this.balance = balance;
	}
	
	// getter/setter
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	
	
	
}
