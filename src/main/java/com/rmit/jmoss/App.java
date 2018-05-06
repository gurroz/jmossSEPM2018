package com.rmit.jmoss;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.rmit.jmoss.util.TableAscii;

public class App
{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try {
            if(Login.loginCheck(scanner)) {
                showMenu(scanner);
            } else {
                System.out.println("Could not log in. Please check credentials.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void showMenu(Scanner scanner) {
        System.out.println("");
        System.out.println("Please enter the option you want to use");
        System.out.println("1. Movie Search");
        System.out.println("2. Book Movie");
        System.out.println("3. Log Out");
        System.out.println("0. Exit");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                showMovieSearch();
                break;
            case 2:
                showMovieBooking();
                break;
            case 3:
            	logout();
            	break;
            case 0:
                exit();
                break;
              default:
            	  System.err.print("Enter a valid value ");
            	  break;


        }

    }

    private static void showMovieSearch() {
        String[] columnNames = {
                "ID",
                "Name",
                "Description"};

        List[][] data = {
                {"1", "Wolverine", "The wolverine movie"},
                {"2", "Superman", "The Superman movie"},
                {"3", "Spiderman", "The Spiderman movie"}
        };

        TableAscii table = new TableAscii(columnNames, data);
        table.printTable();
    }

    private static void showMovieBooking() {
    }
   
    private static void logout() {
   	 System.err.print("\n" + "Logout Successful" + "\n");
   	 main(null);
   }

    private static void exit() {
        System.err.print("Shutdown Successful,Goodbye!!!");
        Toolkit.getDefaultToolkit().beep();
        System.exit(0);
    }
}
