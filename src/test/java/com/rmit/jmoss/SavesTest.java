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
		
		Seat loadSeat = loadTicket.getSeat();
		assertTrue(loadSeat.isTaken());
	}	
	
	@Test
	public void testRemoveTicket () {
		
		// Set up
		DataReadWrite readWrite = new DataReadWrite();
		ArrayList<Screening> screenings = (ArrayList<Screening>) readWrite.loadScreenings();

		ArrayList<Customer> customers = (ArrayList<Customer>) readWrite.loadCustomers();
		Customer startCustomer = customers.get(2);
		
		// Save ticket
		Screening screening = screenings.get(1);
		Seat seat = screening.getSeats().get(1); 
		Ticket saveTicket = new Ticket(null, startCustomer, screening, seat);
		readWrite.saveTicket(saveTicket);
		
		// Load customer
		customers = (ArrayList<Customer>) readWrite.loadCustomers();
		Customer loadCustomer = null;
		for (Customer c : customers) {
			if (c.getEmail().equals(startCustomer.getEmail())) {
				loadCustomer = c;
			}
		}
		assertNotNull(loadCustomer); 
		
		// Load ticket
		Ticket loadTicket = null;
		for (Ticket t : loadCustomer.getTickets()) {
			if ((t.getScreening().getId().equals(saveTicket.getScreening().getId())
					&& (t.getSeat().getNumber().equals(saveTicket.getSeat().getNumber())))) {
				loadTicket = t;
			}
		}
		assertNotNull(loadTicket); 
		
		// Remove ticket
		readWrite.removeTicket(loadTicket);
		
		// Load again and recheck
		// Load customer
		customers = (ArrayList<Customer>) readWrite.loadCustomers();
		Customer reloadCustomer = null;
		for (Customer c : customers) {
			if (c.getEmail().equals(startCustomer.getEmail())) {
				reloadCustomer = c;
			}
		}
		assertNotNull(reloadCustomer); 
		
		// Load ticket
		boolean exists = false;
		for (Ticket t : reloadCustomer.getTickets()) {
			
			System.out.println(t.getId() +"|"+ loadTicket.getId());
			
			if ((t.getId().equals(loadTicket.getId()))) {
				exists = true;
			}
		}
		assertFalse(exists); 
	}
}
