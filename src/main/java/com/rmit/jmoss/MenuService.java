package com.rmit.jmoss;

import com.rmit.jmoss.exceptions.CredentialsTooShortException;
import com.rmit.jmoss.exceptions.FilmNameTooShortException;
import com.rmit.jmoss.exceptions.NotEnoughInformationException;
import com.rmit.jmoss.models.Screening;
import com.rmit.jmoss.models.Ticket;
import com.rmit.jmoss.util.DataReadWrite;
import com.rmit.jmoss.util.TableAscii;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Used to show the informatio and access the controllerc. Use it as a View
 */
public class MenuService {
    private static MenuService instance;
    private Scanner scanner;
    private JMossService jMossService;
    private String homeLogo = "    _ __  __       _____ _____ \n" +
            "   (_)  \\/  |     / ____/ ____|\n" +
            "    _| \\  / | ___| (___| (___  \n" +
            "   | | |\\/| |/ _ \\\\___ \\\\___ \\ \n" +
            "   | | |  | | (_) |___) |___) |\n" +
            "   | |_|  |_|\\___/_____/_____/ \n" +
            "  _/ |                         \n" +
            " |__/                          ";


    public MenuService() {
        this.scanner = new Scanner(System.in);
        this.jMossService = JMossService.getInstance();

    }

    public static MenuService getInstance() {
        if(instance == null) {
            instance = new MenuService();
        }

        return instance;
    }

    public void showLogin() {
        System.out.println(homeLogo);
        System.out.println("Welcome to ABC Cineplex booking system! Please enter with your credentials.");
        System.out.println("");

        System.out.println("Enter your login");
        String login = scanner.nextLine();

        System.out.println("Enter your password");
        String pass = scanner.nextLine();

        try {
            if(jMossService.logClerck(login, pass)) {
                showMainMenu();
            } else {
                System.out.println("Could not log in. Please check credentials.");
            }
        } catch (CredentialsTooShortException e) {
            System.out.println("Could not log in. Please check credentials.");
        }
    }

    private void showMainMenu() {
        System.out.println("");
        System.out.println("Please enter the option you want to use");
        System.out.println("1. Display all Cineplex");
        System.out.println("2. Cineplex Search");
        System.out.println("3. Movie Search");
        System.out.println("4. Search by Ticket");
        System.out.println("5. Log Out");
        System.out.println("0. Exit");

        try {
            int option = scanner.nextInt();


            switch (option) {
                case 1:
                    break;
                case 2:
                	showCineplexSearch();
                    break;
                case 3:
                    showMovieSearch();
                    break;
                case 4:
                    break;
                case 5:
                    logout();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    System.err.println("** Enter a valid option");
                    scanner.nextLine();
                    showMainMenu();
                    break;
            }
        } catch (Exception e) {
            System.err.println("** Enter a valid option");
            scanner.nextLine();
            showMainMenu();
        }
    }

	private void showMovieSearch() {
        System.out.println("");
        System.out.println("* Enter the name of the movie you are looking for: ");

        String movieName = scanner.next();
        try {
            Collection<Screening> searchResults = jMossService.searchByFilmName(movieName);

            List<List<String>> movies = new ArrayList<List<String>>();

            for(Screening screening : searchResults) {
                List<String> movieData = new ArrayList<String>();
                movieData.add(screening.getId());
                movieData.add(screening.getFilmName());
                movieData.add(screening.getCinemaName());
                movieData.add(screening.getDay());
                movieData.add(screening.getTime());
                movies.add(movieData);
            }

            List<String> headers = new ArrayList<String>();

            headers.add("ID");
            headers.add("NAME");
            headers.add("CINEPLEX");
            headers.add("DAY");
            headers.add("TIME");

            // Example on how to print a table
            TableAscii tableResults = new TableAscii(headers, movies);
            tableResults.printTable();

            System.out.println("");
            System.out.println("* Enter the id of the movie to show the detail: ");

            String movieId = scanner.next();
            showMovieDetail(movieId);

        } catch (FilmNameTooShortException e) {
            System.err.println("Enter a valid name, larger that 2 characters");
            showMovieSearch();
        }

    }
	private void showCineplexSearch() {
        System.out.println("\n* Enter the cineplex you are looking for: ");
        String cineplex = scanner.next();
        try {
            Collection<Screening> searchResults = jMossService.searchByCineplex(cineplex);
            if(searchResults.isEmpty()) {
				System.err.println("Cineplex Not Exist");
				showCineplexSearch();
			}
            List<List<String>> movies = new ArrayList<List<String>>();

            for(Screening screening : searchResults) {
                List<String> movieData = new ArrayList<String>();

                movieData.add(screening.getCinemaName());
                movieData.add(screening.getId());
                movieData.add(screening.getFilmName());
                movieData.add(screening.getDay());
                movieData.add(screening.getTime());
                movies.add(movieData);
            }

            List<String> headers = new ArrayList<String>();

            headers.add("CINEPLEX");
            headers.add("ID");
            headers.add("NAME");
            headers.add("DAY");
            headers.add("TIME");

            TableAscii tableResults = new TableAscii(headers, movies);
            tableResults.printTable();

            System.out.println("");
            System.out.println("* Enter the id of the movie to show the detail: ");

            String movieId = scanner.next();
            showMovieDetail(movieId);

        } catch (Exception e) {
            showCineplexSearch();
        }

    }

    private void showMovieDetail(String id) {
        try {
            Screening screening = jMossService.getScreenById(id);
            System.out.printf("Id: %s || Name: %s || Cinema: %S || Day: %s || Time: %s", screening.getId(),screening.getFilmName(), screening.getCinemaName(), screening.getDay(),screening.getTime());
            System.out.printf("\nDescription: ", screening.getDescription());
            if(screening.viewSeats()) {
            	System.out.println("* Enter the seats number you want to book, separated by comas");
                String seats = scanner.next();
                makeBooking(screening, seats);
            } else {
            	System.out.println("* Enter the id of the movie to show the detail: ");
                String movieId = scanner.next();
                showMovieDetail(movieId);
            }
        } catch (FilmNameTooShortException e) {
            e.printStackTrace();
        }
    }

    
    private void makeBooking(Screening screening, String seats) {
    	try {
    		System.out.println("* Enter your email:");
    		String email = scanner.next();
    		System.out.println("* Enter your suburb:");
    		String suburb = scanner.next();

            String[] seatNumbers = seats.split(",");

            List<Ticket> tickets = new ArrayList<Ticket>();
            for(int i = 0; i < seatNumbers.length ; i++) {
                Ticket ticket = jMossService.book(screening.getId(), email, suburb, seatNumbers[i]);
                if(ticket == null) {
                    System.out.println("Could not book the desire seat " + seatNumbers[i] + ". Please try again with a different one.");
                    System.out.println("");

                    showMovieDetail(screening.getId());
                    return;
                }
                tickets.add(ticket);
            }

            System.out.println("Continue with the following tickets booking?");
            for(Ticket ticket : tickets) {
                System.out.println(ticket.printDetails());
            }

            System.out.println("0. No");
            System.out.println("1. Yes");

            try {
                int option = scanner.nextInt();

                if(option == 1) {
                    for(Ticket ticket : tickets) {
                        jMossService.confirmBooking(ticket);
                    }

                    System.out.println("Tickets booked correctly ");
                } else {
                    showMovieDetail(screening.getId());
                    return;
                }
            }  catch (Exception e) {
                System.err.println("** Enter a valid option");
                e.printStackTrace();
                scanner.nextLine();
                makeBooking(screening, seats);
            }

    	} catch(Exception e) {
    		e.printStackTrace();
    	} catch (NotEnoughInformationException e) {
			e.printStackTrace();
		}
    	
    	
	}

	private void logout() {
        System.out.println("\n" + "Logout Successful" + "\n");
        showLogin();
    }

    private void exit() {
        System.err.println("Shutdown Successful, Goodbye!!!");
        Toolkit.getDefaultToolkit().beep();
        System.exit(0);
    }

}
