package com.revature.main;

import java.lang.invoke.SwitchPoint;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.Login;
import com.revature.services.AccountSvc;

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
		}
		
		if (u.isEmployee()) {
			System.out.println("Enter 1 to create an account, or 2 to display member accounts");
			switch(sc.nextInt()) {
			case 1:
				AccountSvc.createAccount();
				break;
			case 2:
				System.out.println("Please enter member number");
//				AccountSvc.getUserAccounts(sc.nextInt());
				List<Account> accnts = AccountSvc.getUserAccounts(sc.nextInt());
				for(Account accnt: accnts) {
					System.out.println("Account number " + accnt.getAccountNumber() + ":");
					System.out.println("Balance " + accnt.getBalance());
				} // end for loop
				break;
			} // end switch
			
			
		} else {
			boolean repeat1 = true;
			
			while(repeat1) {
				
				System.out.println("Enter 1 to view Accounts, 2 to transfer funds, 3 to make a deposit, 4 make a withdrawl, 5 to quit");
				List<Account> accnts = AccountSvc.getUserAccounts(u.getUserId());
				switch (sc.nextInt()) {
				case 1:
					
					for(Account accnt: accnts) {
						System.out.println("Account number " + accnt.getAccountNumber() + ":");
						System.out.println("Balance " + accnt.getBalance());
					} // end for
					break;
				case 2:
					AccountSvc.transferMoney();
					break;
				case 3:
					System.out.println("Please select the account you wish to deposit into.");
					int actNumber = sc.nextInt();
					System.out.println("Enter the ammount you are depositing.");
					int amnt = sc.nextInt();
					if(amnt >= 0) {
						AccountSvc.deposit(actNumber, amnt);
					}else {
						System.out.println("Please use positive whole numbers");
					}
					
					break;
				case 4:
					System.out.println("Please select the account you wish to withdrawl from.");
					int actNumber1 = sc.nextInt();
					System.out.println("Enter the ammount you are withdrawling.");
					int amnt1 = sc.nextInt();
					if(amnt1 >= 0) {
						AccountSvc.withdrawl(actNumber1, amnt1);
					}else {
						System.out.println("Please use whole amounts");
					}
					
					break;
				case 5:
					repeat1 = false;
					break;
				default:
					break;
				}
				
			}
			
			
			
		} // end if
		
		System.out.println("Thank You. Goodbye");
		
		
		
	}

}
