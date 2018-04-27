package main.java.com.rmit.jmoss;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collection;

public class DataReadWrite {

	private FileReader reader;
	private FileWriter writer;

	// Getters and Setters
	public FileReader getReader() {
		return reader;
	}

	public void setReader(FileReader reader) {
		this.reader = reader;
	}

	public FileWriter getWriter() {
		return writer;
	}

	public void setWriter(FileWriter writer) {
		this.writer = writer;
	}
	
	// Public methods
	public Clerk loadClerk(String username) {
		// INCOMPLETE
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
