package com.company;

import java.util.ArrayList;

public class DirectorsFilter implements Filter{

    private String myDirector;

    public DirectorsFilter(String director) {
        myDirector = director;
    }

    @Override
    public boolean satisfies(String id) {

        String movieDirectors = MovieDatabase.getDirector(id);

        String[] directors = myDirector.split(",");

        for(String d: directors){
            if(movieDirectors.contains(d)){
                return true;
            }
        }
        return false;

    }
}
