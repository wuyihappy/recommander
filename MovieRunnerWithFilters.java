package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {

    public void printAverageRatings() throws IOException {
        String moviefile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratedmoviesfull.csv";
        String ratingsfile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratings.csv";

        MovieDatabase.initialize(moviefile);

        ThirdRatings thirdRatings = new ThirdRatings(ratingsfile);
        int ratorSize = thirdRatings.getRaterSize();


        ArrayList<Rating> mininalRatings =  thirdRatings.getAverageRatings(35);
//        Arrays.sort(mininalRatings);
        Collections.sort(mininalRatings);


        for(Rating rating: mininalRatings){
            String movieId = rating.getItem();
            double ratingValue = rating.getValue();

            System.out.println(String.valueOf(ratingValue) + " " + MovieDatabase.getTitle(movieId));
        }

        System.out.println("------------number of raters -----" + String.valueOf(ratorSize));

        System.out.println("----------read data for-movies----" + String.valueOf(MovieDatabase.size()));

        System.out.println("----------minimal rator count number is-----" + String.valueOf(mininalRatings.size()));
    }

    public void printAverageRatingsByYear() throws IOException {
        String moviefile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratedmovies_short.csv";
        String ratingsfile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratings_short.csv";

        MovieDatabase.initialize(moviefile);

        ThirdRatings thirdRatings = new ThirdRatings(ratingsfile);
        int ratorSize = thirdRatings.getRaterSize();

        YearAfterFilter filterCriteria = new YearAfterFilter(2000);

        ArrayList<Rating> mininalRatings =  thirdRatings.getAverageRatingsByFilter(1,filterCriteria );
//        Arrays.sort(mininalRatings);
        Collections.sort(mininalRatings);


        for(Rating rating: mininalRatings){
            String movieId = rating.getItem();
            double ratingValue = rating.getValue();

            System.out.println(String.valueOf(ratingValue) + " " + MovieDatabase.getYear(movieId) + " "+  MovieDatabase.getTitle(movieId));
        }

        System.out.println("------------number of raters -----" + String.valueOf(ratorSize));

        System.out.println("----------read data for-movies----" + String.valueOf(MovieDatabase.size()));

        System.out.println("----------minimal rator count number is-----" + String.valueOf(mininalRatings.size()));
    }


    public void printAverageRatingsByGenre() throws IOException {
        String moviefile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratedmoviesfull.csv";
        String ratingsfile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratings.csv";

        MovieDatabase.initialize(moviefile);

        ThirdRatings thirdRatings = new ThirdRatings(ratingsfile);
        int ratorSize = thirdRatings.getRaterSize();

        AllFilters filterCriteria = new AllFilters();

        GenreFilter filterCriteriaG = new GenreFilter("Drama");
        YearAfterFilter filterCriteriaY = new YearAfterFilter(1990);


        MinutesFilter filterCriteriaM = new MinutesFilter(90, 180);

        DirectorsFilter filterCriteriaD = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");

        filterCriteria.addFilter(filterCriteriaM);
        filterCriteria.addFilter(filterCriteriaD);

        ArrayList<Rating> mininalRatings =  thirdRatings.getAverageRatingsByFilter(3,filterCriteria );
//        Arrays.sort(mininalRatings);
        Collections.sort(mininalRatings);


        for(Rating rating: mininalRatings){
            String movieId = rating.getItem();
            double ratingValue = rating.getValue();

            System.out.println(String.valueOf(ratingValue) + " " + MovieDatabase.getMinutes(movieId) + " "+  MovieDatabase.getTitle(movieId));
        }

        System.out.println("------------number of raters -----" + String.valueOf(ratorSize));

        System.out.println("----------read data for-movies----" + String.valueOf(MovieDatabase.size()));

        System.out.println("----------minimal rator count number is-----" + String.valueOf(mininalRatings.size()));
    }

}
