package com.revature.models;

public class User {
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String userName;
	private String password;
	private boolean employee;
	
	
	public User() {
		
	}	
	
	
	public User(int userId, String firstName, String lastName, String email, String username, String password,
			boolean employee) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userName = username;
		this.password = password;
		this.employee = employee;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEmployee() {
		return employee;
	}


	public void setEmployee(boolean employee) {
		this.employee = employee;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", userName=" + userName + ", password=" + password + ", employee=" + employee + "]";
	}
	
	
	
	
}
