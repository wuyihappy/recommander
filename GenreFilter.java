package com.company;

public class GenreFilter implements Filter{

    private String myGenre;

    public GenreFilter(String genre) {
        myGenre = genre;
    }

    @Override
    public boolean satisfies(String id) {

        String movieGnere = MovieDatabase.getGenres(id);

        return movieGnere.contains(myGenre);

    }
}
