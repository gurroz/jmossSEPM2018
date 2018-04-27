package main.java.com.rmit.jmoss;

public class Seat {

	private String number;
	private boolean taken;
	
	// Constructor
	public Seat (String number, boolean taken) {
		this.setNumber(number);
		this.setTaken(taken);
	}

	// Getters and Setters
	public String getNumber() {
		return number;
	}

	private void setNumber(String number) {
		this.number = number;
	}

	public boolean isTaken() {
		return taken;
	}

	private void setTaken(boolean taken) {
		this.taken = taken;
	}
	
	// Public methods
	public boolean book () {
		// INCOMPLETE
		return false;
	}
	
	public boolean unbook () {
		// INCOMPLETE
		return false;
	}
}
