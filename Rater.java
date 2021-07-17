package com.company;

import java.util.ArrayList;

public interface Rater {
    public String getID();

    void addRating(String movieID, double rating);

    boolean hasRating(String id);

    double getRating(String id);

    ArrayList<String> getItemsRated();
}
