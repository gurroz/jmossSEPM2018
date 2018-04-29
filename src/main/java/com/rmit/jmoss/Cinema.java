package com.rmit.jmoss;

import java.util.Collection;

public class Cinema {

	private String name;
	private Collection<Screening> screenings;
	
	// Constructor
	public Cinema(String name, Collection<Screening> screenings) {
		this.setName(name);
		this.setScreenings(screenings);
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public Collection<Screening> getScreenings() {
		return screenings;
	}

	private void setScreenings(Collection<Screening> screenings) {
		this.screenings = screenings;
	}
	
	// Public methods
	public String viewScreenings () {
		// INCOMPLETE
		return null;
	}
}