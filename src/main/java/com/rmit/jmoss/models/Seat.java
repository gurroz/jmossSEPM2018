package com.rmit.jmoss.models;

public class Seat {

	private String number;
	private Screening screening;
	private boolean taken;
	
	// Constructor
	public Seat (String number, Screening screening, boolean taken) {
		this.setNumber(number);
		this.setScreening(screening);
		this.setTaken(taken);
	}

	// Getters and Setters
	public String getNumber() {
		return number;
	}

	private void setNumber(String number) {
		this.number = number;
	}

	public Screening getScreening() {
		return screening;
	}

	private void setScreening(Screening screening) {
		this.screening = screening;
	}

	public boolean isTaken() {
		return taken;
	}

	private void setTaken(boolean taken) {
		this.taken = taken;
	}
	
	// Public methods
	public boolean book () {
		this.taken = true;
		return false;
	}
	
	public boolean unbook () {
		this.taken = false;
		return false;
	}
}