package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {

    public void printAverageRatings() throws IOException {
        String moviefile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratedmoviesfull.csv";
        String ratingsfile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratings.csv";

        MovieDatabase.initialize(moviefile);

        FourthRatings FourthRatings = new FourthRatings();
        int ratorSize = FourthRatings.getRaterSize();


        ArrayList<Rating> mininalRatings =  FourthRatings.getAverageRatings(35);
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

    public void printAverageRatingsByGenre() throws IOException {
        String moviefile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratedmoviesfull.csv";
        String ratingsfile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratings.csv";

        MovieDatabase.initialize(moviefile);

        RaterDatabase.addRatings(ratingsfile);


        FourthRatings FourthRatings = new FourthRatings();
        int ratorSize = FourthRatings.getRaterSize();

        AllFilters filterCriteria = new AllFilters();

        GenreFilter filterCriteriaG = new GenreFilter("Drama");
        YearAfterFilter filterCriteriaY = new YearAfterFilter(1990);


        MinutesFilter filterCriteriaM = new MinutesFilter(90, 180);

        DirectorsFilter filterCriteriaD = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");

        filterCriteria.addFilter(filterCriteriaM);
        filterCriteria.addFilter(filterCriteriaD);

        ArrayList<Rating> mininalRatings =  FourthRatings.getAverageRatingsByFilter(3,filterCriteria );
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


    public void printSimilarRatings() throws IOException {
        String moviefile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratedmoviesfull.csv";
        String ratingsfile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratings.csv";

        MovieDatabase.initialize(moviefile);
        RaterDatabase.addRatings(ratingsfile);

        FourthRatings FourthRatings = new FourthRatings();
        int ratorSize = FourthRatings.getRaterSize();

        String raterID = "65";
        int numSimilarRaters = 20;
        int minimalRaters = 5;


        ArrayList<Rating> result =  FourthRatings.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);
//        Arrays.sort(mininalRatings);
//        Collections.sort(mininalRatings);


        for(Rating rating: result){
            String movieId = rating.getItem();
            double ratingValue = rating.getValue();

            System.out.println(String.valueOf(ratingValue) + " " + MovieDatabase.getTitle(movieId));
        }

        System.out.println("------------number of raters -----" + String.valueOf(ratorSize));

        System.out.println("----------read data for-movies----" + String.valueOf(MovieDatabase.size()));

        System.out.println("---------result size-----" + String.valueOf(result.size()));
    }

    public void printSimilarRatingsByGenre() throws IOException {
        String moviefile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratedmoviesfull.csv";
        String ratingsfile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratings.csv";

        MovieDatabase.initialize(moviefile);
        RaterDatabase.addRatings(ratingsfile);

        FourthRatings FourthRatings = new FourthRatings();
        int ratorSize = FourthRatings.getRaterSize();

        String raterID = "964";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        GenreFilter filter = new GenreFilter("Mystery");


        ArrayList<Rating> result =  FourthRatings.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filter);
//        Arrays.sort(mininalRatings);
//        Collections.sort(mininalRatings);


        for(Rating rating: result){
            String movieId = rating.getItem();
            double ratingValue = rating.getValue();

            System.out.println(String.valueOf(ratingValue) + " " + MovieDatabase.getTitle(movieId));
        }

        System.out.println("------------number of raters -----" + String.valueOf(ratorSize));

        System.out.println("----------read data for-movies----" + String.valueOf(MovieDatabase.size()));

        System.out.println("---------result size-----" + String.valueOf(result.size()));
    }


    public void printSimilarRatingsByDirector() throws IOException {
        String moviefile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratedmoviesfull.csv";
        String ratingsfile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratings.csv";

        MovieDatabase.initialize(moviefile);
        RaterDatabase.addRatings(ratingsfile);

        FourthRatings FourthRatings = new FourthRatings();
        int ratorSize = FourthRatings.getRaterSize();

        String raterID = "120";
        int numSimilarRaters = 10;
        int minimalRaters = 2;
        DirectorsFilter filterCriteriaD = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");

        ArrayList<Rating> result =  FourthRatings.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteriaD);
//        Arrays.sort(mininalRatings);
//        Collections.sort(mininalRatings);


        for(Rating rating: result){
            String movieId = rating.getItem();
            double ratingValue = rating.getValue();

            System.out.println(String.valueOf(ratingValue) + " " + MovieDatabase.getTitle(movieId));
        }

        System.out.println("------------number of raters -----" + String.valueOf(ratorSize));

        System.out.println("----------read data for-movies----" + String.valueOf(MovieDatabase.size()));

        System.out.println("---------result size-----" + String.valueOf(result.size()));
    }

    public void printSimilarRatingsByGenreAndMinutes() throws IOException {
        String moviefile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratedmoviesfull.csv";
        String ratingsfile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratings.csv";

        MovieDatabase.initialize(moviefile);
        RaterDatabase.addRatings(ratingsfile);

        FourthRatings FourthRatings = new FourthRatings();
        int ratorSize = FourthRatings.getRaterSize();

        AllFilters filterCriteria = new AllFilters();

        String raterID = "168";
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        MinutesFilter filterCriteriaM = new MinutesFilter(80, 160);
        GenreFilter filter = new GenreFilter("Drama");

        filterCriteria.addFilter(filter);
        filterCriteria.addFilter(filterCriteriaM);


        ArrayList<Rating> result =  FourthRatings.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
//        Arrays.sort(mininalRatings);
//        Collections.sort(mininalRatings);


        for(Rating rating: result){
            String movieId = rating.getItem();
            double ratingValue = rating.getValue();

            System.out.println(String.valueOf(ratingValue) + " " + MovieDatabase.getTitle(movieId));
        }

        System.out.println("------------number of raters -----" + String.valueOf(ratorSize));

        System.out.println("----------read data for-movies----" + String.valueOf(MovieDatabase.size()));

        System.out.println("---------result size-----" + String.valueOf(result.size()));
    }

    public void printSimilarRatingsByYearAfterAndMinutes() throws IOException {
        String moviefile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratedmoviesfull.csv";
        String ratingsfile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratings.csv";

        MovieDatabase.initialize(moviefile);
        RaterDatabase.addRatings(ratingsfile);

        FourthRatings FourthRatings = new FourthRatings();
        int ratorSize = FourthRatings.getRaterSize();

        AllFilters filterCriteria = new AllFilters();

        String raterID = "314";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        MinutesFilter filterCriteriaM = new MinutesFilter(70, 200);
        YearAfterFilter filterCriteriaY = new YearAfterFilter(1975);

        filterCriteria.addFilter(filterCriteriaY);
        filterCriteria.addFilter(filterCriteriaM);



        ArrayList<Rating> result =  FourthRatings.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
//        Arrays.sort(mininalRatings);
//        Collections.sort(mininalRatings);


        for(Rating rating: result){
            String movieId = rating.getItem();
            double ratingValue = rating.getValue();

            System.out.println(String.valueOf(ratingValue) + " " + MovieDatabase.getTitle(movieId));
        }

        System.out.println("------------number of raters -----" + String.valueOf(ratorSize));

        System.out.println("----------read data for-movies----" + String.valueOf(MovieDatabase.size()));

        System.out.println("---------result size-----" + String.valueOf(result.size()));
    }

    public void printDotProduct() throws IOException {
        String moviefile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratedmoviesfull.csv";
        String ratingsfile = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratings_4th_4.csv";

        MovieDatabase.initialize(moviefile);
        RaterDatabase.addRatings(ratingsfile);

        FourthRatings fourthRatings = new FourthRatings();
        int ratorSize = fourthRatings.getRaterSize();
        String id1 = "15";
        String id2 = "20";
        double result = fourthRatings.getDotProduct(id1,id2);

        System.out.println("----rater size ----" + String.valueOf(ratorSize));


        System.out.println("---------two raters's dot result----" + String.valueOf(result));

        ArrayList<Rating> test4 = new ArrayList<Rating>();
//        test4 = fourthRatings.getSimilaritiesTest("30");

    }

}
