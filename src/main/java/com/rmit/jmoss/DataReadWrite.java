package com.rmit.jmoss;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
		// INCOMPLETE
		return null;
	}
	
	public Collection<Customer> loadCustomers() {
		// INCOMPLETE
		return null;
	}
	
	public Collection<Screening> loadScreenings() {
		// INCOMPLETE
		return null;
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