package com.revature.models;

public class Account {
	
	private int accountNumber;
	private int memberNumber;
	private int balance;
	public Account(int accountNumber, int memberNumber, int balance) {
		super();
		this.accountNumber = accountNumber;
		this.memberNumber = memberNumber;
		this.balance = balance;
	}
	
	public Account(int memberNumber, int balance) {
		this.memberNumber = memberNumber;
		this.balance = balance;
	}
	
	public Account(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", memberNumber=" + memberNumber + ", balance=" + balance
				+ "]";
	}
	
	

}
