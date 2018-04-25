package com.rmit.jmoss.services;

import com.rmit.jmoss.daos.CinemaDao;
import com.rmit.jmoss.daos.ICinemaDao;
import com.rmit.jmoss.daos.IMovieFunctionDao;
import com.rmit.jmoss.daos.MovieFunctionDao;
import com.rmit.jmoss.models.Cineplex;
import com.rmit.jmoss.models.MovieFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CinemaService implements ICinemaService{

    private ICinemaDao cinemaDao;
    private IMovieFunctionDao movieFunctionDao;

    public CinemaService() {
        cinemaDao = new CinemaDao();
        movieFunctionDao = new MovieFunctionDao();
    }

    @Override
    public List<Cineplex> getCinemas() {
        List<Cineplex> cinemas = cinemaDao.getCinemas();
        for(Cineplex cinema : cinemas) {
            Map<Integer, List<MovieFunction>> moviesFunctionByHour = new HashMap<Integer, List<MovieFunction>>();
            for(int i = 0 ; i < MenuService.MAX_AMOUNT_SHOWS; i++) {
                moviesFunctionByHour.put(i, movieFunctionDao.getRandomMovieFunctions());
            }
            cinema.setMovies(moviesFunctionByHour);
        }

        return cinemas;
    }

    @Override
    public List<String> getCinemasNames() {
        List<String> cinemaNames = new ArrayList<String>();
        for(Cineplex cinema : getCinemas()) {
            cinemaNames.add(cinema.getName());
        }

        return cinemaNames;
    }

    @Override
    public List<Cineplex> searchCinemasByName(String name) {
        List<Cineplex> cinemaNames = new ArrayList<Cineplex>();
        for(Cineplex cinema : getCinemas()) {
            if(cinema.getName().toLowerCase().indexOf(name.toLowerCase()) >= 0) {
                cinemaNames.add(cinema);
            }
        }

        return cinemaNames;
    }

    @Override
    public List<String> searchMoviesByName(String name) {
        return null;
    }

    @Override
    public List<String> getMovieFunctionNames(Integer hour, Cineplex cinema) {
        List<String> moviesNames = new ArrayList<String>();
        for(MovieFunction movie : cinema.getMovies().get(hour)) {
            moviesNames.add(movie.getFunctionName());
        }

        return moviesNames;
    }

    @Override
    public Cineplex getCinemasById(String id) {
        Cineplex result = null;
        List<Cineplex> cinemas = getCinemas();
        for(Cineplex cinema : cinemas) {
           if(cinema.getId().equals(id)) {
               result = cinema;
               break;
           }
        }

        return result;
    }

    @Override
    public MovieFunction getMovieFunctionById(Cineplex cinema, String id) {
        return null;
    }
}
