
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {

    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String raterfile)
    {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(raterfile);
    }
    
    public int getRaterSize()
    {
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters)
    {
        FirstRatings fr = new FirstRatings();
        double av = 0.0;
        int n = fr.numRatingsByMovie(myRaters, id, 0);
        if(n >= minimalRaters)
        {
            for(Rater r: myRaters)
            {
                if(r.getRating(id) != -1)
                {
                    av += r.getRating(id);
                }
            }
            av/=n;
        }
        return av;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
        FirstRatings fr = new FirstRatings();
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> diff_items = MovieDatabase.filterBy(new TrueFilter()); 
        //diff_items = fr.numDiffItems(myRaters, diff_items); 
        for(String s: diff_items)
        {
            double av_rating = getAverageByID(s, minimalRaters);
            Rating rat = new Rating(s, av_rating);
            if(av_rating != 0)
            {
                ratings.add(rat);
            }
        }
        return ratings;
    }
   
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria)
    {
        FirstRatings fr = new FirstRatings();
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> diff_items = MovieDatabase.filterBy(filterCriteria); 
        //diff_items = fr.numDiffItems(myRaters, diff_items); 
        for(String s: diff_items)
        {
            double av_rating = getAverageByID(s, minimalRaters);
            Rating rat = new Rating(s, av_rating);
            if(av_rating != 0)
            {
                ratings.add(rat);
            }
        }
        return ratings;
    }
    
}
