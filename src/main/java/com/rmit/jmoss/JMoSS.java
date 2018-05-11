package com.rmit.jmoss;

import com.rmit.jmoss.models.Cinema;
import com.rmit.jmoss.models.Clerk;
import com.rmit.jmoss.models.Customer;
import com.rmit.jmoss.models.Screening;
import com.rmit.jmoss.models.Seat;
import com.rmit.jmoss.models.Ticket;
import com.rmit.jmoss.util.DataReadWrite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Used to store all the files in memory. Use it as a DAO
 */
public class JMoSS {
	
	private DataReadWrite dataReadWrite;
	private Collection<Cinema> cinemas;
	private Collection<Screening> screenings;
	private Collection<Customer> customers;
	private Collection<Clerk> clerks;
	
	
	// Constructor
	public JMoSS(DataReadWrite dataReadWrite) {
		this.setDataReadWrite(dataReadWrite);
		this.cinemas = dataReadWrite.loadCinemas();
		this.screenings = dataReadWrite.loadScreenings();
		this.customers = dataReadWrite.loadCustomers();
		this.clerks = dataReadWrite.loadClerk();
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

	public Collection<Clerk> getClerks() {
		return clerks;
	}

	private void setClerks(Collection<Clerk> clerks) {
		this.clerks = clerks;
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
	
	public Collection<Screening> searchByFilm (String filmName) {
		List<Screening> screenings = new ArrayList<Screening>();
		for(Screening screening : this.getScreenings()) {
			if(screening.getFilmName().toLowerCase().indexOf(filmName.toLowerCase()) > -1) {
				screenings.add(screening);
			}
		}

		return screenings;
	}
	
	public boolean searchByCustomer () {
		// INCOMPLETE
		return false;
	}

	public Clerk searchClerk(String username, String password) {
		for(Clerk clerks : this.getClerks()) {
			if(clerks.getUsername().equals(username) && clerks.getPassword().equals(password)) {
				return clerks;
			}
		}

		return null;
	}
	
	public Screening getScreening(String id) {
		Screening screen = null;
		for(Screening screening : this.getScreenings()) {
			if(screening.getId().equals(id)) {
				screen = screening;
				break;
			}
		}

		return screen;
	}
	
	public Customer getCustomer(String email) {
		Customer customer = null;
		for(Customer customerS : this.getCustomers()) {
			if(customerS.getEmail().equals(email))
				customer = customerS;
			
		}
		return customer;
		
		
	}
	
	
	
	public boolean addBooking (String screenID, String email, String suburb, String seatNum) {
	
		if (!getScreening(screenID).geatSeatByNumber(seatNum).isTaken()) {
			
			String id = null; //ticket id
			if (getCustomer(email) != null) {
			Ticket ticket = new Ticket(id, getCustomer(email), getScreening(screenID), getScreening(screenID).geatSeatByNumber(seatNum));
			getScreening(screenID).addBooking(ticket);
			dataReadWrite.saveTicket(ticket);
			System.out.println(ticket.getScreening().getCinemaName() + ticket.getScreening().getDay() + ticket.getScreening().getFilmName() 
					+ ticket.getScreening().getTime() + ticket.getSeat()); // prints the details of the booking
			return true;
		} else {
			Customer cust = new Customer(null, email, suburb);
			Ticket ticket = new Ticket(id, cust, getScreening(screenID), getScreening(screenID).geatSeatByNumber(seatNum));
			dataReadWrite.saveTicket(ticket);
			return true;
		}
		
		
	} return false;
	}
	public boolean addBookings () {
		// INCOMPLETE
		return false;
	}
	
	public boolean removeBooking (String idT, String idS ) { //asks for ticket id which needs to be removed and Screening id
		if(getScreening(idS).getTicket(idT) != null) {
			dataReadWrite.removeTicket(getScreening(idS).getTicket(idT));
			return true;
		} else {
			return false;
		}
		
	}
}