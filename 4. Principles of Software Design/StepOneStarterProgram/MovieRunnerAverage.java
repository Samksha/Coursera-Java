
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerAverage {
    
    public void printAverageRatings()
    {
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        System.out.println("Number of movies: " + sr.getMovieSize());
        System.out.println("Number of raters: " + sr.getRaterSize());
        ArrayList<Rating> rats = sr.getAverageRatings(12);
        Collections.sort(rats);
        for(Rating r: rats)
        {
            System.out.println(r.getValue() + " " + sr.getTitle(r.getItem()));
        }
        System.out.println("Number of movies with numratings : " + rats.size());
    }

    public void getAverageRatingOneMovie()
    {        
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        String title = "Vacation";
        System.out.println("Average rating for " + title + " = " + sr.getAverageByID(sr.getID(title), 0));
    }
}
