package com.rmit.jmoss;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collection;

public class DataReadWrite {

	private FileReader reader;
	private FileWriter writer;

	// Getters and Setters
	private FileReader getReader() {
		return reader;
	}

	private void setReader(FileReader reader) {
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
		
		// Load all clerks using file reader
		try {
			setReader(new FileReader("data/tickets.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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