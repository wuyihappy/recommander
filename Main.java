package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        FirstRatings firstRatings = new FirstRatings();
//        String fileName = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratedmovies_short.csv";
//        firstRatings.loadMovies(fileName);

//        firstRatings.testLoadMovies();
//        firstRatings.testLoadRaters();

//        MovieRunnerAverage movieRunnerAverage= new MovieRunnerAverage();
//        movieRunnerAverage.printAverageRatings();
//        movieRunnerAverage.getAverageRatingOneMovie();

//        MovieRunnerWithFilters movieRunnerWithFilters= new MovieRunnerWithFilters();
//        movieRunnerWithFilters.printAverageRatingsByGenre();


//
        MovieRunnerSimilarRatings movieRunnerSimilarRatings= new MovieRunnerSimilarRatings();
//        movieRunnerSimilarRatings.printSimilarRatings();
//        System.out.println("-=====================分割线==========");
        movieRunnerSimilarRatings.printSimilarRatingsByGenre();
//        System.out.println("-=====================分割线==========");
//        movieRunnerSimilarRatings.printSimilarRatingsByYearAfterAndMinutes();

//        movieRunnerSimilarRatings.printDotProduct();


	// write your code here
    }
}
