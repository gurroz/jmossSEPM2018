package com.rmit.jmoss;

import java.util.ArrayList;

import com.rmit.jmoss.models.Cinema;
import com.rmit.jmoss.models.Clerk;
import com.rmit.jmoss.models.Customer;
import com.rmit.jmoss.util.DataReadWrite;
import com.rmit.jmoss.models.Screening;
import com.rmit.jmoss.models.Seat;
import com.rmit.jmoss.models.Ticket;

import org.junit.Test;

import junit.framework.TestCase;

public class TestLoads extends TestCase {

	@Test
	public void testLoadClerk () {
		DataReadWrite readWrite = new DataReadWrite();
		
//		String username = "User1";
//		Clerk clerk = readWrite.loadClerk(username);
//		assertEquals(clerk.getId(), "0001");
//
//		username = "User9";
//		clerk = readWrite.loadClerk(username);
//		assertEquals(clerk.getId(), "0009");
//
//		username = "User15";
//		clerk = readWrite.loadClerk(username);
//		assertEquals(clerk.getId(), "0015");
//
//		username = "NotAUser";
//		clerk = readWrite.loadClerk(username);
//		assertNull(clerk);
	}
	
	@Test
	public void testScreeningLoads () {
		DataReadWrite readWrite = new DataReadWrite();
		
		ArrayList<Screening> screenings = (ArrayList<Screening>) readWrite.loadScreenings();		
		assertEquals(screenings.get(0).getFilmName(), "I Feel Pretty");
		assertEquals(screenings.get(1).getFilmName(), "A Quiet Place");
	}
	
	@Test
	public void testScreeningSeatLoads () {
		DataReadWrite readWrite = new DataReadWrite();
		
		ArrayList<Screening> screenings = (ArrayList<Screening>) readWrite.loadScreenings();
		ArrayList<Seat> seats = (ArrayList<Seat>) screenings.get(0).getSeats();
		assertEquals(seats.get(0).getNumber(), "A1");
		assertTrue(seats.get(0).isTaken());
		assertEquals(seats.get(1).getNumber(), "A2");
		assertFalse(seats.get(1).isTaken());
	}
	
	@Test
	public void testCinemaLoads () {
		DataReadWrite readWrite = new DataReadWrite();
		
		ArrayList<Cinema> cinemas = (ArrayList<Cinema>) readWrite.loadCinemas();
		assertEquals(cinemas.get(0).getName(), "St Kilda");
		assertEquals(cinemas.get(1).getName(), "Fitzroy");
	}
	
	@Test
	public void testCustomerLoads () {
		DataReadWrite readWrite = new DataReadWrite();
		
		ArrayList<Customer> customers = (ArrayList<Customer>) readWrite.loadCustomers();
		assertEquals(customers.get(0).getEmail(), "1@gmail.com");
		assertEquals(customers.get(5).getId(), "0006");
		assertEquals(customers.get(5).getEmail(), "6@gmail.com");
	}
	
	@Test
	public void testCustomerTicketLoads () {
		DataReadWrite readWrite = new DataReadWrite();
		
		ArrayList<Customer> customers = (ArrayList<Customer>) readWrite.loadCustomers();
		ArrayList<Ticket> tickets = (ArrayList<Ticket>) customers.get(5).getTickets();
		assertEquals(tickets.get(0).getId(), "0001");
		assertEquals(tickets.get(0).getSeat().getNumber(), "A1");
		assertEquals(tickets.get(0).getScreening().getFilmName(), "I Feel Pretty");
	}
}
