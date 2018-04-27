package main.java.com.rmit.jmoss;

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
	
	// Public methods
	public boolean login (String password) {
		// INCOMPLETE		
		return false;
	}
}
