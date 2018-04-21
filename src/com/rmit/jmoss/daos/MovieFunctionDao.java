package com.rmit.jmoss.daos;

import com.rmit.jmoss.models.MovieFunction;

import java.util.ArrayList;
import java.util.List;

public class MovieFunctionDao implements IMovieFunctionDao {

    @Override
    public List<MovieFunction> getMovieFunctions() {
        return null;
    }

    @Override
    public List<MovieFunction> getRandomMovieFunctions() {
        MovieFunction movie1 = new MovieFunction(String.valueOf(Math.round(Math.random() * 1000)), "Wolverine");
        MovieFunction movie2 = new MovieFunction(String.valueOf(Math.round(Math.random() * 1000)), "Spiderman");
        MovieFunction movie3 = new MovieFunction(String.valueOf(Math.round(Math.random() * 1000)), "Punisher");
        MovieFunction movie4 = new MovieFunction(String.valueOf(Math.round(Math.random() * 1000)), "Captain America");
        MovieFunction movie5 = new MovieFunction(String.valueOf(Math.round(Math.random() * 1000)), "The Hulk");
        MovieFunction movie6 = new MovieFunction(String.valueOf(Math.round(Math.random() * 1000)), "Black Panther");
        MovieFunction movie7 = new MovieFunction(String.valueOf(Math.round(Math.random() * 1000)), "Antman");

        List<MovieFunction> moviesFunctions = new ArrayList<MovieFunction>();
        if((Math.round(Math.random() * 10)) % 2 == 0) {
            moviesFunctions.add(movie1);
            moviesFunctions.add(movie2);
            moviesFunctions.add(movie3);
            moviesFunctions.add(movie4);
            moviesFunctions.add(movie5);
            moviesFunctions.add(movie6);
            moviesFunctions.add(movie7);
        } else {
            moviesFunctions.add(movie2);
            moviesFunctions.add(movie1);
            moviesFunctions.add(movie5);
            moviesFunctions.add(movie4);
            moviesFunctions.add(movie3);
            moviesFunctions.add(movie7);
            moviesFunctions.add(movie6);
        }

        return moviesFunctions;
    }

    @Override
    public  List<MovieFunction> getMovieFunctionById(String id) {
        return null;
    }

}
