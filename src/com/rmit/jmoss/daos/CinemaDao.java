package com.rmit.jmoss.daos;

import com.rmit.jmoss.models.Cineplex;

import java.util.ArrayList;
import java.util.List;

public class CinemaDao implements ICinemaDao {

    @Override
    public List<Cineplex> getCinemas() {
        List<Cineplex> cinemas =  new ArrayList<Cineplex>();
//        cinemas.add(new Cineplex("1","St Kilda"));
//        cinemas.add(new Cineplex("2","Fitzroy"));
//        cinemas.add(new Cineplex("3","Melbourne CBD"));
//        cinemas.add(new Cineplex("4","Sunshine"));
//        cinemas.add(new Cineplex("5","Lilydale"));

        cinemas.add(new Cineplex("1","Melbourne CBD"));
        cinemas.add(new Cineplex("2","Sunshine"));

        return cinemas;
    }


}
