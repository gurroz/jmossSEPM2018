package com.rmit.jmoss;

import com.rmit.jmoss.exceptions.CredentialsTooShortException;
import com.rmit.jmoss.exceptions.FilmNameTooShortException;
import com.rmit.jmoss.exceptions.NotEnoughInformationException;
import com.rmit.jmoss.models.*;
import com.rmit.jmoss.util.DataReadWrite;

import java.util.Collection;

/**
 * Used to access the information and apply the logic. Use it as a Controller
 */
public class JMossService {
    private static JMossService instance;
    private JMoSS jMoss;


    public JMossService() {
        DataReadWrite dataLoader = new DataReadWrite();
        this.jMoss = new JMoSS(dataLoader);
    }

    public static JMossService getInstance() {
        if(instance == null) {
            instance = new JMossService();
        }

        return instance;
    }

    public Collection<Screening> searchByFilmName(String filmName) throws FilmNameTooShortException {
        if(filmName == null || filmName.length() < 1) {
            throw new FilmNameTooShortException();
        }

        return this.jMoss.searchByFilm(filmName);
    }

    public Collection<String> getAllCineplexes(){
    	return this.jMoss.getAllCineplexes();
    }
    
    public Collection<Screening> searchByCineplex(String cineplex){
    	
    	return this.jMoss.searchByCinema(cineplex);
    }
    
    public boolean logClerk(String user, String password) throws CredentialsTooShortException {
        if(user == null || password == null) {
            throw new CredentialsTooShortException();
        }

        Clerk clerk = this.jMoss.searchClerk(user, password);
        if(clerk != null) {
            return true;
        } else {
            return false;
        }
    }
    

    public Ticket book(String screenID, String email, String suburb, String seatNum) throws NotEnoughInformationException {
    	if (screenID == null || email == null || suburb == null || seatNum == null) {
    	    throw new NotEnoughInformationException();
        }

        Screening screening = jMoss.getScreening(screenID);
    	if(screening == null) {
            throw new NotEnoughInformationException();
        }

        return jMoss.addBooking(screening, email, suburb, seatNum);
    }

    public void confirmBooking(Ticket ticket) throws NotEnoughInformationException {
        if (ticket == null) {
            throw new NotEnoughInformationException();
        }

        jMoss.confirmBooking(ticket);
    }

    
    public boolean deleteBooking (String ticketId) { //asks for ticket id and screening id
    	
    	if (ticketId != null) {    		
    		boolean deleted = jMoss.removeBooking(ticketId);	    	
    		return deleted;
    	} else {
    		return false;
    	}
    }

    public Screening getScreenById(String id) throws FilmNameTooShortException {
        if(id == null) {
            throw new FilmNameTooShortException();
        }

        return this.jMoss.getScreening(id);
    }

	public Customer getCustomer(String email) throws CredentialsTooShortException {
        if (email == null) {
            throw new CredentialsTooShortException();
        }
        
		return jMoss.getCustomer(email);
	}

	public Ticket getTicket(String id) throws CredentialsTooShortException {
        if (id == null) {
            throw new CredentialsTooShortException();
        }
        
		return jMoss.getTicket(id);
	}
}
