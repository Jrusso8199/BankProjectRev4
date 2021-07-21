package com.revature.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;

public class AccountSvc {
	
	private static ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	static Scanner sc = new Scanner(System.in);
	
	public static void createAccount() {
		
		
		
		System.out.println("Open an account");
		int mn;
		System.out.println("Enter Member number");
		mn = sc.nextInt();
		System.out.println("Enter opening balance");
		Account na = new Account(mn, sc.nextInt());
		
		
		try {
			Connection con = conUtil.getConnection();
			//To use our functions/procedure we need to turn off autocommit
			con.setAutoCommit(false);
			String sql = "call create_account(?,?)";
			CallableStatement cs = con.prepareCall(sql);
			
			cs.setInt(1, na.getMemberNumber());
			cs.setInt(2, na.getBalance());
			
			cs.execute();
			
			con.setAutoCommit(true);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static List<Account> getUserAccounts(int memberNumber){

		List<Account> aList = new ArrayList<Account>();
		
		try {
			Connection con = conUtil.getConnection();
			con.setAutoCommit(false);
			//Use this syntax to call a stored function
			String sql = "{?=call get_user_accounts(" + memberNumber + ")}";
			
			CallableStatement cs = con.prepareCall(sql);
			
			cs.registerOutParameter(1, Types.OTHER);
			
			cs.execute();
			
			ResultSet rs = (ResultSet) cs.getObject(1);
			
			while(rs.next()) {
				Account accnt = new Account(rs.getInt(1), rs.getInt(2), rs.getInt(3));
				aList.add(accnt);
			}
			
			con.setAutoCommit(true);
			return aList;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	
	public static void transfer(Account a, Account b) {
		
		
		
	}
	
	public static int getAccountBalance(int accNum) {
		int rtrn = 0;
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT balance FROM accounts WHERE account_number = " + accNum;
			
			
			Statement st = con.createStatement();			
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				rtrn = rs.getInt(1);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rtrn;
	}
	
	public static void updateBalance(int accountNumber, int balance) {
		
		try {
			Connection con = conUtil.getConnection();
			String sql = "UPDATE accounts SET balance = ?" + " WHERE account_number = ?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, balance);
			st.setInt(2, accountNumber);
			st.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		
	}

	public static void transferMoney() {
		System.out.println("which account would you like to transfer FROM?");
		int a = sc.nextInt();
		System.out.println("Which account would you like to transfer TO?");
		int b = sc.nextInt();
		int aBalance = AccountSvc.getAccountBalance(a);
		int bBalance = AccountSvc.getAccountBalance(b);
		
		System.out.println("How much to transfer");
		int amount = sc.nextInt();
		if(amount < aBalance) {
			aBalance -= amount;
			AccountSvc.updateBalance(a, aBalance);
			bBalance += amount;
			AccountSvc.updateBalance(b, bBalance);
	}else {
		System.out.println("Ammount exedes account balance. Please Try again.");
	}
	
	}

	public static void deposit(int accountNumber, int amount) {
		int theBalance = getAccountBalance(accountNumber);
		theBalance += amount;
		updateBalance(accountNumber, theBalance);
	}
	
	public static void withdrawl(int accountNumber, int amount) {
		int theBalance = getAccountBalance(accountNumber);
		if((theBalance - amount) >= 0){
			theBalance -= amount;
			updateBalance(accountNumber, theBalance);
		} else {
			System.out.println("Insuffencient funds!");
		}
		
	}

}
