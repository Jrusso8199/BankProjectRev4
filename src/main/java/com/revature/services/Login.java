package com.revature.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.revature.exceptions.InvalidCredentialsException;
import com.revature.exceptions.UserDoesNotExistException;
import com.revature.exceptions.UserNameAlreadyExistsException;
import com.revature.logging.Logging;
import com.revature.models.User;

public class Login {
	
	static ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	static Scanner sc = new Scanner(System.in);
	
	
	public static User login() throws UserDoesNotExistException, InvalidCredentialsException{		
		
		User user = new User();
		
		System.out.println("Please enter your username");
		user.setUserName(sc.nextLine());
		
		System.out.println("Please enter your password");
		user.setPassword(sc.nextLine());


		try {
			Connection con = conUtil.getConnection();
			
			String sql = "SELECT * FROM users u WHERE u.username = '" + user.getUserName() + "' AND u.password = '" + user.getPassword() + "'";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				user.setUserId(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setUserName(rs.getString(5));
				user.setPassword(rs.getString(6));
				user.setEmployee(rs.getBoolean(7));
			}
		return user;
			
		} catch(SQLException e) {
			Logging.logger.warn("User tried loggging in that does not exist");
			throw new UserDoesNotExistException();
		}
	}
	
	public static User signup() throws UserNameAlreadyExistsException{
		
		User user = new User();
		
		System.out.println("Please enter your First Name:");
		user.setFirstName(sc.nextLine());
		System.out.println("Please enter your Last Name:");
		user.setLastName(sc.nextLine());
		System.out.println("Please enter your desired Username:");
		user.setUserName(sc.nextLine());
		System.out.println("Please enter your desired Password:");
		user.setPassword(sc.nextLine());
		System.out.println("Please enter your Email address:");
		user.setEmail(sc.nextLine());
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "INSERT INTO users(first_name, last_name, email, username, password) values"
					+ "(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getUserName());
			ps.setString(5, user.getPassword());
			
			ps.execute();
			
			return user;
			
		} catch (SQLException e) {
			Logging.logger.warn("Username created that already exists in the database");
			throw new UserNameAlreadyExistsException();
		}
	}
	
	
	
}
