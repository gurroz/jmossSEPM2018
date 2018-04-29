package com.rmit.jmoss;

public class Ticket {

	private Customer customer;
	private Screening screening;
	private Seat seat;
	
	// Constructor
	public Ticket(Customer customer, Screening screening, Seat seat) {
		this.setCustomer(customer);
		this.setScreening(screening);
		this.setSeat(seat);
	}

	// Getters and Setters
	public Customer getCustomer() {
		return customer;
	}

	private void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Screening getScreening() {
		return screening;
	}

	private void setScreening(Screening screening) {
		this.screening = screening;
	}

	public Seat getSeat() {
		return seat;
	}

	private void setSeat(Seat seat) {
		this.seat = seat;
	}
	
	// Public methods
	@Override
	public String toString () {
		// INCOMPLETE
		return null;
	}
}