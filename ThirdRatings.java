package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThirdRatings {

    private ArrayList<EfficientRater> myRaters;


    public ThirdRatings() throws IOException{
        this("ratings.csv");
    }

    public ThirdRatings( String ratingsfile) throws IOException {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsfile);

    }

    private double getAverageByID(String movieID, int minimalRaters){

        int numOfRatingsForMovie = 0;
        double totlalRatings = 0.0;


//        System.out.println(myRaters.size());

        List<Integer> ratignsCount = new ArrayList<Integer>();


        for(EfficientRater rater: myRaters){
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


    public int getRaterSize(){
        return myRaters.size();
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> minimalRatings = new ArrayList<Rating>();

        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        Set<String> setMovieId = new HashSet<String>();

        for (EfficientRater rater : myRaters) {

            List<String> itemList = rater.getItemsRated();
            for (String item : itemList) {

//                System.out.println("rating movie item id " + item);
//                System.out.println("rating given  " + String.valueOf(rater.getRating(item)));
                double averageRatingOneItem = getAverageByID(item, minimalRaters);
                if ( averageRatingOneItem> 0.0) {
                    if(!setMovieId.contains(item)){
                        if(movies.contains(item)) {
                            Rating minimalRating = new Rating(item, averageRatingOneItem);
                            minimalRatings.add(minimalRating);
                            setMovieId.add(item);
                        }

                    }

                }

            }

        }
        return minimalRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){

        ArrayList<Rating> minimalRatings = new ArrayList<Rating>();

        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

        Set<String> setMovieId = new HashSet<String>();

        for (EfficientRater rater : myRaters) {

            List<String> itemList = rater.getItemsRated();
            for (String item : itemList) {

//                System.out.println("rating movie item id " + item);
//                System.out.println("rating given  " + String.valueOf(rater.getRating(item)));
                double averageRatingOneItem = getAverageByID(item, minimalRaters);
                if ( averageRatingOneItem> 0.0) {
                    if(!setMovieId.contains(item)){
                        if(movies.contains(item)) {
                            Rating minimalRating = new Rating(item, averageRatingOneItem);
                            minimalRatings.add(minimalRating);
                            setMovieId.add(item);
                        }

                    }

                }

            }

        }
        return minimalRatings;

    }



}
