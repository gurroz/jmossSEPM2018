package com.rmit.jmoss.models;

import java.util.ArrayList;

public class Screening {

	private String id;
	private String filmName;
	private String cinemaName;
	private String day;
	private String time;
	private String description;
	private ArrayList<Seat> seats = new ArrayList<Seat>();
	private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	
	// Constructor
	public Screening(String id, String filmName, String cinemaName, String day, 
			String time, String description) {
		this.setId(id);
		this.setFilmName(filmName);
		this.setCinemaName(cinemaName);
		this.setTime(time);
		this.setDay(day);
		this.setDescription(description);
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}
	
	public String getFilmName() {
		return filmName;
	}

	private void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	private void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getDay() {
		return day;
	}

	private void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	private void setTime(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Seat> getSeats() {
		return seats;
	}

	public ArrayList<Ticket> getTickets() {
		return tickets;
	}
	
	// Public methods
	public void addSeat (Seat seat) {
		this.seats.add(seat);
	}
	
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