package com.revature.exceptions;

import java.util.Scanner;

import com.revature.services.Login;

public class InvalidCredentialsException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidCredentialsException() {
		super("User provided invalid credentials");
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Username or password is invalid. Press 1 to try again or 2 to quit.");
		switch (sc.nextInt()) {
		case 1:
			Login.login();
			break;
		case 2:
			System.out.println("Goodbye");
			System.exit(0);
		default:
			break;
		}
	}

}
