package com.rmit.jmoss;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login {
	public static void loginCheck() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("LogPass"));
		Scanner input = new Scanner(System.in);
		sc.useDelimiter("[,\n]");
		
		System.out.println("Enter your login");
		String login = input.nextLine();
		
		System.out.println("Enter your password");
		String pass = input.nextLine();
		
		boolean found = false;
		
		while(sc.hasNext()){
			
			String fileLogin = sc.next();
			String filePass = sc.next();
		
			if (login.trim().equals(fileLogin.trim()) && pass.trim().equals(filePass.trim())) {
				found = true;
			} 
		}
		if (found == true) {
			System.out.println("You are logged in!");
		} else {
			System.out.println("Wrong inputs");
		}
		
		
		sc.close();
		input.close();
		
		
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		Login.loginCheck();
	}

}
