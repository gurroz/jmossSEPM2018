package com.rmit.jmoss.models;

import java.util.ArrayList;

public class Customer {

	private String id;
	private String email;
	private String suburb;
	private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	
	// Constructor
	public Customer (String id, String email, String suburb) {
		this.setId(id);
		this.setEmail(email);
		this.setSuburb(suburb);
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
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

	public ArrayList<Ticket> getTickets() {
		return tickets;
	}
	
	// Public methods
	public void addTicket(Ticket ticket) {
		this.tickets.add(ticket);
	}
	
}