package com.rmit.jmoss;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login {
	public static boolean loginCheck(Scanner input) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("LogPass"));
		sc.useDelimiter("[,\n]");

		String homeLogo = "    _ __  __       _____ _____ \n" +
				"   (_)  \\/  |     / ____/ ____|\n" +
				"    _| \\  / | ___| (___| (___  \n" +
				"   | | |\\/| |/ _ \\\\___ \\\\___ \\ \n" +
				"   | | |  | | (_) |___) |___) |\n" +
				"   | |_|  |_|\\___/_____/_____/ \n" +
				"  _/ |                         \n" +
				" |__/                          ";

		System.out.println(homeLogo);
		System.out.println("Welcome to ABC Cineplex booking system! Please enter with your credentials.");
		System.out.println("");

		System.out.println("Enter your login");
		String login = input.nextLine();

		System.out.println("Enter your password");
		String pass = input.nextLine();

		boolean found = false;

		while (sc.hasNext()) {

			String fileLogin = sc.next();
			String filePass = sc.next();

			if (login.trim().equals(fileLogin.trim()) && pass.trim().equals(filePass.trim())) {
				found = true;
			}
		}

		sc.close();

		return found;
	}
}
