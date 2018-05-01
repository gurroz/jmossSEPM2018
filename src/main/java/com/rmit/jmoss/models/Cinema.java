package com.rmit.jmoss.models;

import java.util.ArrayList;
import java.util.Collection;

public class Cinema {

	private String name;
	private Collection<Screening> screenings = new ArrayList<Screening>();
	
	// Constructor
	public Cinema(String name) {
		this.setName(name);
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
	
	// Public methods
	public void addScreening(Screening screening) {
		this.screenings.add(screening);
	}
	
	public String viewScreenings () {
		// INCOMPLETE
		return null;
	}
}