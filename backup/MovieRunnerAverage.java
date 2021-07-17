package com.company.backup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {

    public void printAverageRatings() throws IOException {
        String moviefile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratedmoviesfull.csv";
        String ratingsfile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratings.csv";

        SecondRatings secondRatings = new SecondRatings(moviefile, ratingsfile);
        int movieSize = secondRatings.getMovieSize();
        int ratorSize = secondRatings.getRaterSize();

        ArrayList<Rating> mininalRatings=  secondRatings.getAverageRatings(12);
//        Arrays.sort(mininalRatings);
        Collections.sort(mininalRatings);



        for(Rating rating: mininalRatings){
            String movieId = rating.getItem();
            double ratingValue = rating.getValue();

            System.out.println(String.valueOf(ratingValue) + " " + secondRatings.getTitle(movieId));
        }

        System.out.println("-------------movie size-----" + String.valueOf(movieSize));
        System.out.println("-------------ratoer size-----" + String.valueOf(ratorSize));
        System.out.println("----------minimal rator count number is-----" + String.valueOf(mininalRatings.size()));
    }


    public void getAverageRatingOneMovie() throws IOException {
        String moviefile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratedmoviesfull.csv";
        String ratingsfile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratings.csv";

        SecondRatings secondRatings = new SecondRatings(moviefile, ratingsfile);
        int movieSize = secondRatings.getMovieSize();
        int ratorSize = secondRatings.getRaterSize();

        String specialTitle = "Vacation";

        ArrayList<Rating> mininalRatings=  secondRatings.getAverageRatings(3);
//        Arrays.sort(mininalRatings);
        Collections.sort(mininalRatings);

        String specailMovieID = secondRatings.getMovieID(specialTitle);

        for(Rating rating: mininalRatings){
            String movieId = rating.getItem();
            double ratingValue = rating.getValue();
            if(specailMovieID.equals(movieId)){
                System.out.println(String.valueOf(ratingValue) + " " + secondRatings.getTitle(movieId));
            }


        }

        System.out.println("-------------movie size-----" + String.valueOf(movieSize));
        System.out.println("-------------ratoer size-----" + String.valueOf(ratorSize));
    }
}
