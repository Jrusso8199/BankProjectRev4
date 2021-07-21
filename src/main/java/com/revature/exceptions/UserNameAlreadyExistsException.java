package com.revature.exceptions;

import java.util.Scanner;

import com.revature.services.Login;

public class UserNameAlreadyExistsException extends RuntimeException{

	
	
	private static final long serialVersionUID = 1L;
	
	public UserNameAlreadyExistsException() {
		super("A username was created that already exists in the database");
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Username already exists. Press 1 to try again or 2 to quit.");
		switch (sc.nextInt()) {
		case 1:
			Login.signup();
			break;
		case 2:
			System.out.println("Goodbye");
			System.exit(0);
		default:
			break;
		}
		
	}

}
