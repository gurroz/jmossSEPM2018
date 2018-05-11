package com.rmit.jmoss.util;

import com.rmit.jmoss.models.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataReadWrite {

	private BufferedReader reader;
	private FileWriter writer;

	// Getters and Setters
	private void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	private FileWriter getWriter() {
		return writer;
	}

	private void setWriter(FileWriter writer) {
		this.writer = writer;
	}
	
	// Public methods
	public Collection<Clerk> loadClerk() {
		
		// Set up the reader
		try {
			setReader(new BufferedReader(new FileReader("data/clerks.txt")));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		ArrayList<Clerk> clerks = new ArrayList<Clerk>();

		// Read in line
		try {

			String line = reader.readLine();
			while (line != null) {
				
				// If line starts with '#', skip line
				if (line.startsWith("#")) {
					line = reader.readLine();
					continue;
				}
				
				// Split into tokens by "|"
				String[] tokens = line.split("\\|");
				String id = tokens[0];
				String user = tokens[1];
				String pass = tokens[2];
				
				clerks.add(new Clerk(id, user, pass));

				// Go to next line
				line = reader.readLine();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return clerks;
	}
	
	public Collection<Cinema> loadCinemas() {
		
		// Set up cinema list
		ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
		
		// Load screenings
		ArrayList<Screening> screenings = (ArrayList<Screening>) loadScreenings();

		// If a cinema of this name doesn't exist, make it
		for (Screening screening : screenings) {
			
			// Find appropriate cinema
			Cinema cinema = null;
			for (Cinema c : cinemas) {
				if (c.getName().equals(screening.getCinemaName())) {
					cinema = c;
				}
			}
			
			// If cinema doesn't exist, add it
			if (cinema == null) {
				cinema = new Cinema(screening.getCinemaName());
				cinemas.add(cinema);
			}
			
			// Add to cinema
			cinema.addScreening(screening);
		}
		
		return cinemas;
	}
	
	public Collection<Customer> loadCustomers() {
		
		// Set up customers list
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		// Set up the reader
		try {
			setReader(new BufferedReader(new FileReader("data/customers.txt")));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		// Read in line
		try {
			String line = reader.readLine();
			while (line != null) {
				
				// If line starts with '#', skip line
				if (line.startsWith("#")) {
					line = reader.readLine();
					continue;
				}
				
				// Split into tokens by "|"
				String[] tokens = line.split("\\|");
				String id = tokens[0];
				String email = tokens[1];
				String suburb = tokens[2];
				
				// Make customer
				Customer customer = new Customer(id, email, suburb);
				
				// Add customer to list
				customers.add(customer);
				
				// Go to next line
				line = reader.readLine();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		// Load screenings to associate with tickets
		ArrayList<Screening> screenings = (ArrayList<Screening>) loadScreenings();
		
		// Set up the reader
		try {
			setReader(new BufferedReader(new FileReader("data/tickets.txt")));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		// Read in line
		try {
			String line = reader.readLine();
			while (line != null) {
				
				// If line starts with '#', skip line
				if (line.startsWith("#")) {
					line = reader.readLine();
					continue;
				}
				
				// Split into tokens by "|"
				String[] tokens = line.split("\\|");
				String id = tokens[0];
				String screeningID = tokens[1];
				String customerID = tokens[2];
				String seatNum = tokens[3];
				
				// Find screening
				Screening screening = null;
				for (Screening s : screenings) {
					if (s.getId().equals(screeningID))
						screening = s;
				}
				
				// Find seat
				Seat seat = null;
				for (Seat s : screening.getSeats()) {
					if (s.getNumber().equals(seatNum))
						seat = s;
				}
				
				// Find customer
				Customer customer = null;
				for (Customer c : customers) {
					if (c.getId().equals(customerID)) {
						customer = c;
						break;
					}
				}
				
				// Make ticket
				Ticket ticket = new Ticket(id, customer, screening, seat);
				
				// Add ticket to customer
				customer.addTicket(ticket);
				
				// Go to next line
				line = reader.readLine();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		
		return customers;
	}
	
	public Collection<Screening> loadScreenings() {
		
		// Set up screenings
		ArrayList<Screening> screenings = new ArrayList<Screening>();
		
		// Set up the reader
		try {
			setReader(new BufferedReader(new FileReader("data/screenings.txt")));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		// Read in line
		try {
			String line = reader.readLine();
			while (line != null) {
				
				// If line starts with '#', skip line
				if (line.startsWith("#")) {
					line = reader.readLine();
					continue;
				}
				
				// Split into tokens by "|"
				String[] tokens = line.split("\\|");
				String id = tokens[0];
				String name = tokens[1];
				String cinemaName = tokens[2];
				String day = tokens[3];
				String time = tokens[4];
				String description = tokens[5];
				
				// Make screening
				Screening screening = new Screening(id, name, cinemaName, day, time, description);
				
				// Add seats
				for (int i = 6; i < tokens.length; i++) {
					
					// First character is 0 free, 1 taken
					boolean taken = false;
					if (tokens[i].charAt(0) == '1')
						taken = true;
						
					// Set up seat
					String number = tokens[i].substring(1);
					Seat seat = new Seat(number, screening, taken);
					
					// Add seat to screening
					screening.addSeat(seat);
				}
				
				// Add screening to list
				screenings.add(screening);
				
				// Go to next line
				line = reader.readLine();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return screenings;
	}
	
	public boolean saveTicket(Ticket ticket) {
		
		// Load all customers (and thus, tickets)
		ArrayList<Customer> customers = (ArrayList<Customer>) loadCustomers();
		
		// SAVING CUSTOMER
		// Go through customers and see if the ticket's customer is new
		boolean exists = false;
		int newID = 0;
		Customer customer = ticket.getCustomer();
		for (Customer c : customers) {
			
			// Check if customer exists
			if (c.getEmail().toLowerCase().equals(customer.getEmail().toLowerCase())) {
				exists = true;
				ticket.setCustomer(c);
				break;
			}
			
			// Update id to next smallest number
			newID = Integer.parseInt(c.getId()) + 1;
		}
		
		// If doesn't exist, generate new id and save customer
		if (!exists) {
			
			// Make new id and add customer to customer list
			String id = String.format("%04d", newID);
			customer.setId(id);
			customers.add(customer);
			
			// Set up writer
			try {
				setWriter(new FileWriter("data/customers.txt", true));
			} 
			catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			
			// Make customer string and append to file
			String customerString = String.format("\n%s|%s|%s", customer.getId(), customer.getEmail(),
					customer.getSuburb());
			try {
				this.writer.append(customerString);
				writer.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		// SAVING TICKET
		// Set up the reader
		try {
			setReader(new BufferedReader(new FileReader("data/tickets.txt")));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		// Read in documentation lines
		ArrayList<String> strings = new ArrayList<String>();
		try {
			String line = reader.readLine();
			while (line != null) {
				
				// If line starts with '#', store line
				if (line.startsWith("#")) {
					strings.add(line + "\n");
				}
				
				line = reader.readLine();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}	
		
		// Go through all existing tickets
		exists = false;
		newID = 0;
		for (Customer c : customers) {
			for (Ticket t : c.getTickets()) {
				
				// Add to save list
				String line = String.format("%s|%s|%s|%s\n", t.getId(), t.getScreening().getId(),
						t.getCustomer().getId(), t.getSeat().getNumber());
				strings.add(line);
//				System.err.print(line);
				
				// Find smallest new id
				newID = Integer.parseInt(t.getId()) + 1;
			}
		}
		
		// Set up text for new ticket
		String id = String.format("%04d", newID);
		String line = String.format("%s|%s|%s|%s\n", id, ticket.getScreening().getId(),
				ticket.getCustomer().getId(), ticket.getSeat().getNumber());
		strings.add(line);
//		System.err.print(line);
		
		// Set up writer
		try {
			setWriter(new FileWriter("data/tickets.txt"));
		} 
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		// Print ticket strings to doc
		try {
			for (String s : strings) {
				writer.append(s);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		// SAVING SEAT
		// Set up the reader
		try {
			setReader(new BufferedReader(new FileReader("data/screenings.txt")));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		// Read in documentation lines
		strings = new ArrayList<String>();
		try {
			line = reader.readLine();
			while (line != null) {
				
				// If line starts with '#', store line
				if (line.startsWith("#")) {
					strings.add(line + "\n");
				}
				
				line = reader.readLine();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}	
		
		// Load all screenings (and thus, seats)
		ArrayList<Screening> screenings = (ArrayList<Screening>) loadScreenings();
		
		// Go through all existing screenings
		exists = false;
		newID = 0;
		for (Screening sc : screenings) {
			
			// Add to save list
			line = String.format("%s|%s|%s|%s|%s|%s", sc.getId(), sc.getFilmName(),
					sc.getCinemaName(), sc.getDay(), sc.getTime(), sc.getDescription());
			
			for (Seat s : sc.getSeats()) {
				
				// If the seat is being booked, set it to be so
				if ((sc.getId().equals(ticket.getScreening().getId()) && 
						(s.getNumber().equals(ticket.getSeat().getNumber())))){
					s.book();
				}
				
				// Add all seats to the line
				int takenInt = 0;
				if (s.isTaken())
					takenInt = 1;
				line = line.concat(String.format("|%d%s", takenInt, s.getNumber()));
			}
			line = line.concat("\n");
			strings.add(line);
		}
				
		// Set up writer
		try {
			setWriter(new FileWriter("data/screenings.txt"));
		} 
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		// Print ticket strings to doc
		try {
			for (String s : strings) {
				writer.append(s);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean removeTicket(Ticket ticket) {
		// INCOMPLETE
		return false;
	}
}