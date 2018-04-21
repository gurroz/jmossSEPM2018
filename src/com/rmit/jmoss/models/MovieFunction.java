package com.rmit.jmoss.models;

import java.util.ArrayList;
import java.util.List;

public class MovieFunction {
    private String id;
    private String movie;
    private String detail;
    private List<String> seatsAvailable;
    private List<String> seatsTaken;
    private final  String[] SEAT_NAMES = {
            "A",
            "B",
            "C",
            "D"};

    public MovieFunction(String id, String movie) {
        this.id = id;
        this.movie = movie;
        this.seatsAvailable = new ArrayList<String>();
        this.seatsTaken = new ArrayList<String>();
        this.detail = "Movie about a super hero";

        for(String seatName : SEAT_NAMES) {
            for(int i = 1; i <= 5; i++ ) {
                seatsAvailable.add(seatName + i);
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public List<String> getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(List<String> seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public List<String> getSeatsTaken() {
        return seatsTaken;
    }

    public void setSeatsTaken(List<String> seatsTaken) {
        this.seatsTaken = seatsTaken;
    }


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean bookSeat(String seat) {
        if(seatsAvailable.contains(seat)) {
            seatsAvailable.remove(seat);
            seatsTaken.add(seat);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeBooking(String seat) {
        if(seatsTaken.contains(seat)) {
            seatsTaken.remove(seat);
            seatsAvailable.add(seat);
            return true;
        } else {
            return false;
        }
    }

    public String getFunctionName() {
        return this.getId() + " " + this.getMovie();
    }

    public String getAvailableSeats() {
        return String.join(",", this.seatsAvailable);
    }

    public String getTakenSeats() {
        return String.join(",", this.seatsTaken);
    }

    public List<String> getMovieDetail() {
        List<String> movieDetail = new ArrayList<String>();
        movieDetail.add(this.getId());
        movieDetail.add(this.getMovie());
        movieDetail.add(this.getDetail());
        movieDetail.add(this.getAvailableSeats());

        return movieDetail;
    }
}
