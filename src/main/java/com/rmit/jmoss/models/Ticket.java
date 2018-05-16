package com.rmit.jmoss.models;

public class Ticket {

	private String id;
	private Customer customer;
	private Screening screening;
	private Seat seat;
	
	// Constructor
	public Ticket(String id, Customer customer, Screening screening, Seat seat) {
		this.setId(id);
		this.setCustomer(customer);
		this.setScreening(screening);
		this.setSeat(seat);
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
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

	public String printDetails() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cinema: ");
		builder.append(this.getScreening().getCinemaName());
		builder.append(" || Film: ");
		builder.append(this.getScreening().getFilmName());
		builder.append(" || Time: ");
		builder.append(this.getScreening().getTime());
		builder.append(" || Seat: ");
		builder.append(this.getSeat().getNumber());

		return builder.toString();
	}
}