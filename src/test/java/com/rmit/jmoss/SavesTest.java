package com.rmit.jmoss;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.rmit.jmoss.models.Customer;
import com.rmit.jmoss.models.Screening;
import com.rmit.jmoss.models.Seat;
import com.rmit.jmoss.models.Ticket;
import com.rmit.jmoss.util.DataReadWrite;

public class SavesTest {

	@Test
	public void testSaveTicketWithNewCustomer() {

		DataReadWrite readWrite = new DataReadWrite();
		ArrayList<Screening> screenings = (ArrayList<Screening>) readWrite.loadScreenings();
		
		Customer saveCustomer = new Customer(null, "16@gmail.com", "Glenhuntly");
		Screening screening = screenings.get(0);
		Seat seat = screening.getSeats().get(5); 
		Ticket saveTicket = new Ticket(null, saveCustomer, screening, seat);
		readWrite.saveTicket(saveTicket);
		
		ArrayList<Customer> customers = (ArrayList<Customer>) readWrite.loadCustomers();
		Customer loadCustomer = null;
		for (Customer c : customers) {
			if (c.getEmail().equals(saveCustomer.getEmail())) {
				loadCustomer = c;
			}
		}
		assertNotNull(loadCustomer); 
		
		Ticket loadTicket = null;
		for (Ticket t : loadCustomer.getTickets()) {
			if ((t.getScreening().getId().equals(saveTicket.getScreening().getId())
					&& (t.getSeat().getNumber().equals(saveTicket.getSeat().getNumber())))) {
				loadTicket = t;
			}
		}
		assertNotNull(loadTicket); 
	}	

	@Test
	public void testSaveTicketWithExistingCustomer() {

		DataReadWrite readWrite = new DataReadWrite();
		ArrayList<Screening> screenings = (ArrayList<Screening>) readWrite.loadScreenings();

		ArrayList<Customer> customers = (ArrayList<Customer>) readWrite.loadCustomers();
		Customer startCustomer = customers.get(1);
		
		Screening screening = screenings.get(0);
		Seat seat = screening.getSeats().get(5); 
		Ticket saveTicket = new Ticket(null, startCustomer, screening, seat);
		readWrite.saveTicket(saveTicket);
		
		customers = (ArrayList<Customer>) readWrite.loadCustomers();
		Customer loadCustomer = null;
		for (Customer c : customers) {
			if (c.getEmail().equals(startCustomer.getEmail())) {
				loadCustomer = c;
			}
		}
		assertNotNull(loadCustomer); 
		
		Ticket loadTicket = null;
		for (Ticket t : loadCustomer.getTickets()) {
			if ((t.getScreening().getId().equals(saveTicket.getScreening().getId())
					&& (t.getSeat().getNumber().equals(saveTicket.getSeat().getNumber())))) {
				loadTicket = t;
			}
		}
		assertNotNull(loadTicket); 
	}	
}
