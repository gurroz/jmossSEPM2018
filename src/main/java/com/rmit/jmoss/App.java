package main.java.com.rmit.jmoss;
import java.awt.Toolkit;
import java.util.Scanner;
import main.java.com.rmit.jmoss.util.TableAscii;

public class App
{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        login(scanner);
        showMenu(scanner);
    }

    private static void login(Scanner scanner) {
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

        // create a scanner so we can read the command-line input

        //  prompt for the user's name
        System.out.print("User id: ");

        // get their input as a String
        String username = scanner.next();

        // prompt for their age
        System.out.print("Password: ");

        // get the age as an int
        String password = scanner.next();
    }

    private static void showMenu(Scanner scanner) {
        System.out.println("");
        System.out.println("Please enter the option you want to use");
        System.out.println("1. Movie Search");
        System.out.println("2. Book Movie");
        System.out.println("9. Logout");
        System.out.println("0. Exit");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                showMovieSearch();
                break;
            case 2:
                showMovieBooking();
                break;
            case 9:
                logout();
                break;
            case 0:
                exit();
                break;
            default:
                System.err.out("Enter a valid option!");
                break;

        }

    }

    private static void showMovieSearch() {
        String[] columnNames = {
                "ID",
                "Name",
                "Description"};

        String[][] data = {
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
        System.err.println("Logout Successful");
        main(null);
    }

    private static void exit() {
        System.err.println("Shutdown Successful!");
        Toolkit.getDefaultToolkit().beep();
		    System.exit(0);
    }
}
