package com.rmit.jmoss.models;

import java.util.ArrayList;
import java.util.List;

import com.rmit.jmoss.util.TableAscii;

public class Screening {

	private String id;
	private String filmName;
	private String cinemaName;
	private String day;
	private String time;
	private String description;
	private ArrayList<Seat> seats = new ArrayList<Seat>();
	private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	
	// Constructor
	public Screening(String id, String filmName, String cinemaName, String day, 
			String time, String description) {
		this.setId(id);
		this.setFilmName(filmName);
		this.setCinemaName(cinemaName);
		this.setTime(time);
		this.setDay(day);
		this.setDescription(description);
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}
	
	public String getFilmName() {
		return filmName;
	}

	private void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	private void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getDay() {
		return day;
	}

	private void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	private void setTime(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Seat> getSeats() {
		return seats;
	}

	public ArrayList<Ticket> getTickets() {
		return tickets;
	}
	
	// Public methods
	public void addSeat (Seat seat) {
		this.seats.add(seat);
	}
	
	public void viewSeats () {
		// WORKING
    	if(seatsFull(seats)) {return;}
		List<String> sr1 = new ArrayList<String>();
        List<String> sr2 = new ArrayList<String>();
        List<String> sr3 = new ArrayList<String>();
        List<String> sr4 = new ArrayList<String>();
    	for(Seat seat:seats) {
    		String[] tokens = seat.getNumber().split("");
			String r = tokens[0];
			String c = tokens[1];
			if(r.equals("A"))
				if(!seat.isTaken()) {sr1.add(seat.getNumber());}
				else{sr1.add("N/A");}
			if(r.equals("B"))
				if(!seat.isTaken()) {sr2.add(seat.getNumber());}
				else{sr2.add("N/A");}
			if(r.equals( "C"))
				if(!seat.isTaken()) {sr3.add(seat.getNumber());}
				else{sr3.add("N/A");}
			if(r.equals("D"))
				if(!seat.isTaken()) {sr4.add(seat.getNumber());}
				else{sr4.add("N/A");}
    	}
    	List<List<String>> sl = new ArrayList<List<String>>();
    	sl.add(sr1);
    	sl.add(sr2);
    	sl.add(sr3);
    	sl.add(sr4);
    	List<String> headers = new ArrayList<String>();
    	headers.add("");
    	headers.add("");
    	headers.add("");
    	headers.add("");
    	headers.add("");

		TableAscii table = new TableAscii(headers,sl);
		table.printTable();
		return;

	}
	
	private boolean seatsFull(ArrayList<Seat> seats) {
		for(int i=0; i<seats.size();i++){
			if(!((ArrayList<Seat>) seats).get(i).isTaken()) {
				return false;
			}
		}
		System.err.println("SORRY - No seats available this session");
		return true;
	}
	
	public boolean addBooking (Ticket ticket) {
		tickets.add(ticket);
		return false;
	}
	
	public boolean removeBooking (Ticket ticket) {
		tickets.remove(ticket);
		return false;
	}

	public Seat geatSeatByNumber(String seatNumber) {
		Seat seatResp = null;
		for(Seat seat : this.seats){
			if(!seat.getNumber().equals(seatNumber)) {
				return seatResp;
			}
		}
		return seatResp;
	}
}