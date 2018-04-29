package com.rmit.jmoss;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

public class TestLoads extends TestCase {

	@Test
	public void testLoadClerk () {
		DataReadWrite readWrite = new DataReadWrite();
		
		String username = "User1";
		Clerk clerk = readWrite.loadClerk(username);
		assertEquals(clerk.getId(), "0001");
		
		username = "User9";
		clerk = readWrite.loadClerk(username);
		assertEquals(clerk.getId(), "0009");
		
		username = "User15";
		clerk = readWrite.loadClerk(username);
		assertEquals(clerk.getId(), "0015");
		
		username = "NotAUser";
		clerk = readWrite.loadClerk(username);
		assertNull(clerk);
	}
	
	@Test
	public void testScreeningLoads () {
		DataReadWrite readWrite = new DataReadWrite();
		
		ArrayList<Screening> screenings = (ArrayList<Screening>) readWrite.loadScreenings();
		assertEquals(screenings.get(0).getFilmName(), "I Feel Pretty");
		assertEquals(screenings.get(1).getFilmName(), "A Quiet Place");
	}
	
	@Test
	public void testCinemaLoads () {
		DataReadWrite readWrite = new DataReadWrite();
		
		ArrayList<Cinema> cinemas = (ArrayList<Cinema>) readWrite.loadCinemas();
		assertEquals(cinemas.get(0).getName(), "St Kilda");
		assertEquals(cinemas.get(1).getName(), "Fitzroy");
	}
}
