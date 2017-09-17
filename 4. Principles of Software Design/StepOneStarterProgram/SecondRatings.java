
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings(String moviefile, String raterfile) {
        // default constructor
        //this("ratedmoviesfull.csv", "ratings.csv");
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(raterfile);
    }
    
    public int getMovieSize()
    {
        return myMovies.size();
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
        ArrayList<String> diff_items = new ArrayList<String>();
        diff_items = fr.numDiffItems(myRaters, diff_items); 
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
    
    public String getTitle(String id)
    {
        for(Movie m: myMovies)
        {
            if(m.getID().equals(id))
                return m.getTitle();
        }
        return "No ID found";
    }
    
    public String getID(String title)
    {
        for(Movie m:myMovies)
        {
            if(m.getTitle().equals(title))
                return m.getID();
        }
        return "No title found";
    }
}
