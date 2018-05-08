package com.rmit.jmoss.models;

public class Clerk {
	
	private String id;
	private String username;
	private String password;
	
	// Constructor
	public Clerk (String id, String username, String password) {
		this.setId(id);
		this.setUsername(username);
		this.setPassword(password);
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	private void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Clerk clerk = (Clerk) o;

		if (!id.equals(clerk.id)) return false;
		if (!username.equals(clerk.username)) return false;
		return password.equals(clerk.password);
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + username.hashCode();
		result = 31 * result + password.hashCode();
		return result;
	}
}