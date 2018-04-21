package com.rmit.jmoss.daos;

import com.rmit.jmoss.models.MovieFunction;

import java.util.List;

public interface IMovieFunctionDao {
    List<MovieFunction> getMovieFunctions();
    List<MovieFunction> getRandomMovieFunctions();
    List<MovieFunction> getMovieFunctionById(String id);

}
