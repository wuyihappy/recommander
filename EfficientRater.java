package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {

        return myRatings.containsKey(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        return myRatings.get(item).getValue();

    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
//        ArrayList<String> list = new ArrayList<String>();
//        for(int k=0; k < myRatings.size(); k++){
//            list.add(myRatings.get(k).getItem());
//        }

        ArrayList<String> result = new ArrayList<String>(myRatings.keySet().stream().collect(Collectors.toList()));

        return result;
    }
}
