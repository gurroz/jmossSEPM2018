package com.rmit.jmoss;

import com.rmit.jmoss.models.*;
import com.rmit.jmoss.util.DataReadWrite;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class LoadsTest extends TestCase {

	@Test
	public void testLoadClerk () {
		DataReadWrite readWrite = new DataReadWrite();

		Clerk searchedClerk = new Clerk("0001", "User1","mypassword01");
		Collection<Clerk> clerks = readWrite.loadClerk();

		boolean found = false;
		for(Clerk clerk : clerks) {
			if(clerk.equals(searchedClerk)) {
				found = true;
				break;
			}
		}
		assertEquals(found, true);

		Clerk notExisting = new Clerk("0fd1", "fdfs","rewre");

		found = false;
		for(Clerk clerk : clerks) {
			if(clerk.equals(notExisting)) {
				found = true;
				break;
			}
		}
		assertEquals(found, false);
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
