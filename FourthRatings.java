package com.company;

/**
 * Write a description of FourthRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {

    private double getAverageByID(String ID,int minimalRaters)
    {
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        double numOfRaters = 0.0;
        double totalRating = 0.0;

        for(Rater r : raters)
        {
            if(r.hasRating(ID))
            {
                totalRating += r.getRating(ID);
                numOfRaters++;
            }
        }

        if(numOfRaters >= minimalRaters)
        {
            double Avg = totalRating/numOfRaters;
            return Avg;
        }

        return 0.0;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
        ArrayList<Rating> ratingsData =new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for(String m : movies)
        {
            double avgRating = getAverageByID(m, minimalRaters);
            if(avgRating != 0.0)
            {
                Rating rating = new Rating(m, avgRating);
                ratingsData.add(rating);
            }
        }
        return ratingsData;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria)
    {
        ArrayList<Rating> ratingData = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

        for(String m : movies)
        {
            double total = getAverageByID(m, minimalRaters);
            if(total != 0.0)
            {
                Rating rating = new Rating(m, total);
                ratingData.add(rating);
            }
        }
        return ratingData;
    }

    private double dotProduct(Rater me, Rater r)
    {
        ArrayList<String> myRatings = me.getItemsRated();

        double dotProduct = 0.0;
        for(String movieID : myRatings)
        {
            if(r.hasRating(movieID))
            {
                double myRate = me.getRating(movieID)-5;
                double rRate  = r.getRating(movieID)-5;
                dotProduct += myRate*rRate;
            }

        }
        return dotProduct;
    }

    public double getDotProduct(String raterID1, String raterID2){
        EfficientRater me = (EfficientRater) RaterDatabase.getRater(raterID1);
        EfficientRater r = (EfficientRater) RaterDatabase.getRater(raterID2);

        return dotProduct(me, r);

    }

    private ArrayList<Rating> getSimilarities(String id)
    {
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        ArrayList<Rating> similarity = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);

        for(Rater r : raters)
        {
            if(!r.getID().equals(id))
            {
                double product = dotProduct(me, r);
                if(product > 0.0)
                {
                    similarity.add(new Rating(r.getID(), product));
                }
            }
        }
        Collections.sort(similarity, Collections.reverseOrder());

        return similarity;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters)
    {
        ArrayList<Rating> list = new ArrayList<Rating> ();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        HashMap<String,Double> accumulatedRating = new HashMap<String,Double> ();
        HashMap<String,Integer> accumulatedCount = new HashMap<String,Integer> ();

        for (String movieID : movies) {
            double total = 0.0;
            int count = 0;

            for (int k=0; k < numSimilarRaters; k++) {
                Rating r = similarRaters.get(k);
                String raterID = r.getItem();
                double weight = r.getValue();

                Rater rater = RaterDatabase.getRater(raterID);

                if (rater.hasRating(movieID)) {
                    double rating = rater.getRating(movieID) * weight;
                    total += rating;
                    count += 1;
                }
            }

            if (count >= minimalRaters) {
                accumulatedRating.put(movieID, total);
                accumulatedCount.put(movieID, count);

                double averageRating = total / count;
                Rating indexRating = new Rating(movieID, averageRating);
                list.add(indexRating);
            }
        }


        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    public int getRaterSize(){
        return RaterDatabase.getRaters().size();
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria)
    {
        ArrayList<Rating> weightedRatings = new ArrayList<Rating> ();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<String> filteredMovies = MovieDatabase.filterBy(filterCriteria);

        HashMap<String,Double> accumulatedRating = new HashMap<String,Double> ();
        HashMap<String,Integer> accumulatedCount = new HashMap<String,Integer> ();

        for (String movieID : filteredMovies) {
            double total = 0.0;
            int count = 0;

            for (int k=0; k < numSimilarRaters; k++) {
                Rating r = similarRaters.get(k);
                String raterID = r.getItem();
                double weight = r.getValue();

                Rater rater = RaterDatabase.getRater(raterID);

                if (rater.hasRating(movieID)) {
                    double rating = rater.getRating(movieID) * weight;
                    total += rating;
                    count += 1;
                }
            }

            if (count >= minimalRaters) {
                accumulatedRating.put(movieID, total);
                accumulatedCount.put(movieID, count);
            }
        }

        for (String movieID : accumulatedRating.keySet()) {
            double weightedRating = Math.round((accumulatedRating.get(movieID) / accumulatedCount.get(movieID)) * 100.0) / 100.0;
            Rating rating = new Rating (movieID, weightedRating);
            weightedRatings.add(rating);
        }

        Collections.sort(weightedRatings, Collections.reverseOrder());
        return weightedRatings;
    }




}
