package com.rmit.jmoss;

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
}
