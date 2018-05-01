package com.rmit.jmoss.util;

import com.rmit.jmoss.models.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class DataReadWrite {

	private BufferedReader reader;
	private FileWriter writer;

	// Getters and Setters
	private BufferedReader getReader() {
		return reader;
	}

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
	public Clerk loadClerk(String username) {
		
		// Set up the reader
		try {
			setReader(new BufferedReader(new FileReader("data/clerks.txt")));
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
				String user = tokens[1];
				String pass = tokens[2];
				
				// Only make clerk object if user matches
				if (user.equals(username)) {
					return new Clerk(id, user, pass);
				}
				
				// Go to next line
				line = reader.readLine();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
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
		// INCOMPLETE
		return null;
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
					Seat seat = new Seat(number, taken);
					
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
	
	public boolean saveTicket() {
		// INCOMPLETE
		return false;
	}
	
	public boolean removeTicket() {
		// INCOMPLETE
		return false;
	}
}