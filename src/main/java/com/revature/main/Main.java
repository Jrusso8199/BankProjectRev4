package com.revature.main;

import java.util.Scanner;

import com.revature.models.User;
import com.revature.services.Login;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to Ruseli Discount National Bank");
		System.out.println("Press 1 to login or 2 to signup");
		User u = new User();
		
		switch (sc.nextInt()) {
		case 1:
			u = Login.login();
			break;
		case 2:
			u = Login.signup();
			u = Login.login();
			break;
		default:
			break;
		}
		if(u.getUserId()==0) {
			System.out.println("Username or password inccorect. Would you like to signup?:");
			System.out.println("Press 1 for yes, 2 to try agian, any other key to quit");
			switch (sc.nextInt()) {
			case 1:
				u = Login.signup();
				break;
			case 2:
				u = Login.login();
			default:
				break;
			}
		}else {
			System.out.println("doesn't work" + u.getUserId());
			
		}
		
		System.out.println(u.toString());
		
		
		
	}

}
