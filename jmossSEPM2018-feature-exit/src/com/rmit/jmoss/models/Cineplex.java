package com.rmit.jmoss.models;

import java.util.List;
import java.util.Map;

public class Cineplex {
    private String id;
    private String name;
    private Map<Integer, List<MovieFunction>> movies;


    public Cineplex() {}

    public Cineplex(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, List<MovieFunction>> getMovies() {
        return movies;
    }

    public void setMovies(Map<Integer, List<MovieFunction>> movies) {
        this.movies = movies;
    }

    public MovieFunction getMovieFunctionById(String id) {
        for(Integer day : this.movies.keySet()) {
            for(MovieFunction movieFunction : this.movies.get(day)) {
                if(id.equals(movieFunction.getId())) {
                    return movieFunction;
                }
            }
        }

        return null;
    }
}
