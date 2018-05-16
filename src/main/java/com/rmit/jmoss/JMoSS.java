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
	private ArrayList<Ticket> customerTickets;
	
	
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
	
	public Collection<Screening> searchByCinema (String cineplex) {
		List<Screening> screenings = new ArrayList<Screening>();
	
		for(Screening screening : this.getScreenings()) {
			if(screening.getCinemaName().toLowerCase().indexOf(cineplex.toLowerCase()) > -1){
				screenings.add(screening);
			}
		}
		
		return screenings;
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
		
	public Ticket addBooking(Screening screening, String email, String suburb, String seatNum) {
		Seat seat = screening.getSeatByNumber(seatNum);
		if (seat != null && !seat.isTaken()) {
			Customer cust = getCustomer(email);
			if (cust == null) {
				cust = new Customer(null, email, suburb);
			}

			Ticket ticket = new Ticket(null, cust, screening, seat);
			cust.addTicket(ticket);
			return ticket;
		}
		return null;
	}

	public void confirmBooking(Ticket ticket) {
		dataReadWrite.saveTicket(ticket);
	}
	
	public boolean removeBooking (String ticketId) {
		
		// asks for ticket id which needs to be removed and Screening id
		if(getTicket(ticketId) != null) {
			dataReadWrite.removeTicket(getTicket(ticketId));
			return true;
		} else {
			return false;
		}		
	}
	
	public ArrayList<Ticket> getCustomerTickets(String email) { //asks for customer email and return all the tickets purchased
		customerTickets = null;
		for (Ticket t : getCustomer(email).getTickets()) {
			customerTickets.add(t);
		}
		return customerTickets;
	}
	
	public Ticket getTicket (String id) { 
		
		//asks for ticket id and return ticket
		Ticket ticket = null;
		for (Customer c : customers) {
			for (Ticket t : c.getTickets()) {
				if (t.getId().equals(id))
					ticket = t;
			}
		}
		return ticket;
	}
}