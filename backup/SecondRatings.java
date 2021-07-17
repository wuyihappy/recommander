package com.company.backup;

/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    private double getAverageByID(String movieID, int minimalRaters){

        int numOfRatingsForMovie = 0;
        double totlalRatings = 0.0;


//        System.out.println(myRaters.size());

        List<Integer> ratignsCount = new ArrayList<Integer>();


        for(Rater rater: myRaters){
            String id = rater.getID();
            int numRatings = rater.numRatings();
            ratignsCount.add(numRatings);


            List<String> itemList = rater.getItemsRated();
            for(String item: itemList){

//                System.out.println("rating movie item id " + item);
//                System.out.println("rating given  " + String.valueOf(rater.getRating(item)));
                if(movieID.equals(item)){
                    numOfRatingsForMovie++;
                    totlalRatings += rater.getRating(item);
                }


            }

        }
        if(numOfRatingsForMovie >= minimalRaters ){
//            System.out.println("rating count for movie id " + movieID);
//            System.out.println("rating count is " + String.valueOf(numOfRatingsForMovie));
//            System.out.println("rating total double number is " + String.valueOf(totlalRatings));

            return totlalRatings / numOfRatingsForMovie;
        }

        return 0.0;

    }

    public SecondRatings() throws IOException {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String moviefile, String ratingsfile) throws IOException {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(moviefile);
        myRaters = firstRatings.loadRaters(ratingsfile);

    }

    public int getMovieSize(){
        return myMovies.size();
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> minimalRatings = new ArrayList<Rating>();

        Set<String> setMovieId = new HashSet<String>();

        for (Rater rater : myRaters) {

            List<String> itemList = rater.getItemsRated();
            for (String item : itemList) {

//                System.out.println("rating movie item id " + item);
//                System.out.println("rating given  " + String.valueOf(rater.getRating(item)));
                double averageRatingOneItem = getAverageByID(item, minimalRaters);
                if ( averageRatingOneItem> 0.0) {
                    if(!setMovieId.contains(item)){
                        Rating minimalRating = new Rating(item, averageRatingOneItem);
                        minimalRatings.add(minimalRating);
                        setMovieId.add(item);


                    }

                }

            }

        }
        return minimalRatings;
    }

    public String getTitle(String movieID){

        String movidIdNotFound = "movie id not found: " + movieID;
        boolean found = false;

        for(Movie movie: myMovies){
            if(movie.getID().equals(movieID)){
                return movie.getTitle();
            }
        }

        return movidIdNotFound;

    }

    public String getMovieID(String title){

        String notFound = "NO SUCH TITLE: " + title;
        boolean found = false;

        for(Movie movie: myMovies){
            if(movie.getTitle().equals(title)){
                return movie.getID();
            }
        }

        return notFound;

    }

}