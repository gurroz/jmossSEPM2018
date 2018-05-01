package com.rmit.jmoss;

import com.rmit.jmoss.models.*;
import com.rmit.jmoss.util.DataReadWrite;

import java.util.Collection;

public class JMoSS {
	
	private DataReadWrite dataReadWrite;
	private Collection<Cinema> cinemas;
	private Collection<Screening> screenings;
	private Collection<Customer> customers;
	private Clerk clerk;
	
	// Constructor
	public JMoSS(DataReadWrite dataReadWrite, Collection<Cinema> cinemas, 
			Collection<Screening> screenings, Collection<Customer> customers) {
		this.setDataReadWrite(dataReadWrite);
		this.setCinemas(cinemas);
		this.setScreenings(screenings);
		this.setCustomers(customers);
	}

	// Getters and Setters
	public DataReadWrite getDataReadWrite() {
		return dataReadWrite;
	}

	private void setDataReadWrite(DataReadWrite dataReadWrite) {
		this.dataReadWrite = dataReadWrite;
	}

	public Collection<Cinema> getCinemas() {
		return cinemas;
	}

	private void setCinemas(Collection<Cinema> cinemas) {
		this.cinemas = cinemas;
	}

	public Collection<Screening> getScreenings() {
		return screenings;
	}

	private void setScreenings(Collection<Screening> screenings) {
		this.screenings = screenings;
	}

	public Collection<Customer> getCustomers() {
		return customers;
	}

	private void setCustomers(Collection<Customer> customers) {
		this.customers = customers;
	}

	public Clerk getClerk() {
		return clerk;
	}

	private void setClerk(Clerk clerk) {
		this.clerk = clerk;
	}

	// Public methods
	public boolean login () {
		// INCOMPLETE
		return false;
	}
	
	public boolean logout () {
		// INCOMPLETE
		return false;
	}
	
	public boolean searchByCinema () {
		// INCOMPLETE
		return false;
	}
	
	public boolean searchByFilm () {
		// INCOMPLETE
		return false;
	}
	
	public boolean searchByCustomer () {
		// INCOMPLETE
		return false;
	}
	
	public boolean viewScreening () {
		// INCOMPLETE
		return false;
	}
	
	public boolean addBooking () {
		// INCOMPLETE
		return false;
	}
	
	public boolean addBookings () {
		// INCOMPLETE
		return false;
	}
	
	public boolean removeBooking () {
		// INCOMPLETE
		return false;
	}
}