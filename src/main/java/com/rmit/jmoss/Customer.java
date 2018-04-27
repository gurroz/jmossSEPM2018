package main.java.com.rmit.jmoss;

import java.util.Collection;

public class Customer {

	private String email;
	private String suburb;
	private Collection<Ticket> tickets;
	
	// Constructor
	public Customer (String email, String suburb, Collection<Ticket> tickets) {
		this.setEmail(email);
		this.setSuburb(suburb);
		this.setTickets(tickets);
	}

	// Getters and Setters
	public String getEmail() {
		return email;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	public String getSuburb() {
		return suburb;
	}

	private void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public Collection<Ticket> getTickets() {
		return tickets;
	}

	private void setTickets(Collection<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	// Public methods
	public void addTickets(Ticket ticket) {
		this.tickets.add(ticket);
	}
}
