package com.rmit.jmoss;

import com.rmit.jmoss.exceptions.CredentialsTooShortException;
import com.rmit.jmoss.exceptions.FilmNameTooShortException;
import com.rmit.jmoss.exceptions.NotEnoughInformationException;
import com.rmit.jmoss.models.Clerk;
import com.rmit.jmoss.models.Customer;
import com.rmit.jmoss.models.Screening;
import com.rmit.jmoss.models.Seat;
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
        if(filmName == null || filmName.length() < 3) {
            throw new FilmNameTooShortException();
        }

        return this.jMoss.searchByFilm(filmName);
    }
    
    public Collection<Screening> searchByCineplex(String cineplex){
    	
    	return this.jMoss.searchByCinema(cineplex);
    }
    public boolean logClerck(String user, String password) throws CredentialsTooShortException {
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
    
    public boolean book(String bookCust, String event, String bookSeat) throws NotEnoughInformationException {
        if(bookCust == null || event == null || bookSeat == null) {
            throw new NotEnoughInformationException();
        }

    	return jMoss.addBooking(bookCust, event, bookSeat);
    
    }
    
    public boolean deleteBooking (Customer bookCust, Screening event, Seat bookSeat) {
    	return jMoss.removeBooking(bookCust, event, bookSeat);
    }

    public Screening getScreenById(String id) throws FilmNameTooShortException {
        if(id == null) {
            throw new FilmNameTooShortException();
        }

        return this.jMoss.getScreening(id);
    }
}
