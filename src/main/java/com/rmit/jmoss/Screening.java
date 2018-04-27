package main.java.com.rmit.jmoss;

import java.util.Collection;

public class Screening {

	private String filmName;
	private String time;
	private Collection<Seat> seats;
	private Collection<Ticket> tickets;
	
	// Constructor
	public Screening(String filmName, String time, Collection<Seat> seats, Collection<Ticket> tickets) {
		this.setFilmName(filmName);
		this.setTime(time);
		this.setSeats(seats);
		this.setTickets(tickets);
	}

	// Getters and Setters
	public String getFilmName() {
		return filmName;
	}

	private void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public String getTime() {
		return time;
	}

	private void setTime(String time) {
		this.time = time;
	}

	public Collection<Seat> getSeats() {
		return seats;
	}

	private void setSeats(Collection<Seat> seats) {
		this.seats = seats;
	}

	public Collection<Ticket> getTickets() {
		return tickets;
	}

	private void setTickets(Collection<Ticket> tickets) {
		this.tickets = tickets;
	}	
	
	// Public methods
	public String viewSeats () {
		// INCOMPLETE
		return null;
	}
	
	public boolean addBooking (Customer customer, Seat seat) {
		// INCOMPLETE
		return false;
	}
	
	public boolean removeBooking (Ticket ticket) {
		// INCOMPLETE
		return false;
	}
}
