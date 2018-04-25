package com.rmit.jmoss.services;

import com.rmit.jmoss.models.Cineplex;
import com.rmit.jmoss.models.MovieFunction;

import java.util.List;

public interface ICinemaService {

    List<Cineplex> getCinemas();
    List<String> getCinemasNames();
    List<Cineplex> searchCinemasByName(String name);
    List<String> searchMoviesByName(String name);
    List<String> getMovieFunctionNames(Integer hour, Cineplex cinema);
    Cineplex getCinemasById(String id);
    MovieFunction getMovieFunctionById(Cineplex cinema, String id);
}
