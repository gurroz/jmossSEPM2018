package com.rmit.jmoss.views;

import java.awt.Toolkit;
import java.util.List;

public class ConsoleOutput implements IConsoleOutput {
    private final String homeLogo = "    _ __  __       _____ _____ \n" +
            "   (_)  \\/  |     / ____/ ____|\n" +
            "    _| \\  / | ___| (___| (___  \n" +
            "   | | |\\/| |/ _ \\\\___ \\\\___ \\ \n" +
            "   | | |  | | (_) |___) |___) |\n" +
            "   | |_|  |_|\\___/_____/_____/ \n" +
            "  _/ |                         \n" +
            " |__/                          ";

    private IOutputPrinter printer;


    public ConsoleOutput() {
        printer = new OutputPrinter();
    }

    public void login() {
        printer.printMessage(homeLogo);
        printer.printMessage("Welcome to ABC Cineplex booking system! Please enter with your credentials.");
        printer.printMessage("");
        printer.printMessage("* User id: ");
    }

    public void mainMenu() {
        printer.printMessage("");
        printer.printMessage("* Please enter the option you want to use");
        printer.printMessage(" 1. Display all Cineplex");
        printer.printMessage(" 2. Cineplex search");
        printer.printMessage(" 3. Movie search");
        printer.printMessage(" 4. Logout");
        printer.printMessage(" 0. Exit");
    }

    public void cineplexList(List<String> cinemas) {
        printer.printMessage("");
        printer.printMessage("* Please enter the cineplex option");
        printer.printMessage(" 0. Back");
        int i = 1;
        for(String cinema : cinemas) {
            printer.printMessage(" " + i +". " + cinema);
            i++;
        }
    }


    public void movieFunctionSelect() {
        printer.printMessage("");
        printer.printMessage("* Enter the id of the movie to check it's details");
        printer.printMessage(" 0. Back");
    }

    public void movieFunctionOperationSelect() {

        printer.printMessage("");
        printer.printMessage("* Select the operation you want to perform");
        printer.printMessage(" 0. Back");
        printer.printMessage(" 1. Book seats");
        printer.printMessage(" 2. Unbook seats");
    }

    public void movieFunctionBook() {
        printer.printMessage("");
        printer.printMessage("* Select seats you want to book (separated by comas)");
        printer.printMessage(" 0. Back");
    }

    public void movieFunctionUnbook() {
        printer.printMessage("");
        printer.printMessage("* Select seats you want to unbook (separated by comas)");
        printer.printMessage(" 0. Back");
    }

    public void movieFunctionBookSuccesfull() {
        printer.printMessage("*** Seats booked correctly.");
    }

    public void movieFunctionBookError() {
        printer.printMessage("*** Error trying to book the seats. Check that they are available.");
    }

    public void movieFunctionUnbookSuccesfull() {
        printer.printMessage("*** Seats unbooked correctly.");
    }

    public void movieFunctionUnbookError() {
        printer.printMessage("*** Error trying to unbook the seats. Check that they are booked.");
    }

    public void freeCinemaSearch() {
        printer.printMessage("* Enter the name of the cineplex you are looking.");
    }

    public void freeMovieSearch() {
        printer.printMessage("* Enter the name of the movie you are looking.");
    }

    public void exit() {
        printer.printMessage("Shutdown Successful,Goodbye!!!");
        Toolkit.getDefaultToolkit().beep();
		System.exit(0); 
    }

    public void logout() {
        printer.printMessage("successfully logged out");
    }

}
