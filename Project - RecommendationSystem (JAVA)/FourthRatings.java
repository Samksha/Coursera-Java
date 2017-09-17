
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {
    
	public double getAverageByID(String id, int minimalRaters)
    {
        RaterDatabase rd = new RaterDatabase();
        rd.initialize("ratings.csv");
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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
    
    private double dotProduct(Rater me, Rater r)
    {
        ArrayList<String> arr = me.getItemsRated();
        double dP = 0;
        for(String s: arr)
        {
            if(r.hasRating(s))
            {
                    double a = me.getRating(s) - 5;
                    double b = r.getRating(s) - 5;
                    dP += a*b;
            }
        }
        return dP;
    }
    
    private ArrayList<Rating> getSimilarities(String id)
    {
        ArrayList<Rating> arr = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        
        for(Rater r: RaterDatabase.getRaters())
        {
            if(!r.equals(me))
            {
                double dP = dotProduct(me , r);
                if(dP > 0)
                    arr.add(new Rating(r.getID(), dP));
            }
        }
        Collections.sort(arr, Collections.reverseOrder());
        return arr;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRatings, int minimalRaters)
    {
        ArrayList<Rating> res = new ArrayList<Rating>();
        ArrayList<Rating> arr = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String s: movies)
        {
            double av = 0, sum = 0, count = 0;
            for(int i = 0; i < numSimilarRatings; i++)
            {
                Rating r = arr.get(i);
                double a = r.getValue();
                String rID = r.getItem();
                Rater rater = RaterDatabase.getRater(rID);
                if(rater.hasRating(s))
                {
                    count++;
                    sum+= a*rater.getRating(s);
                }
             
            }
            if(count > minimalRaters)
            {
                av = sum/count;
                res.add(new Rating(s, av));
            }
        }
        Collections.sort(res, Collections.reverseOrder());
        return res;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRatings, int minimalRaters, Filter filterCriteria)
    {
        ArrayList<Rating> res = new ArrayList<Rating>();
        ArrayList<Rating> arr = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String s: movies)
        {
            double av = 0, sum = 0, count = 0;
            for(int i = 0; i < numSimilarRatings; i++)
            {
                Rating r = arr.get(i);
                double a = r.getValue();
                String rID = r.getItem();
                Rater rater = RaterDatabase.getRater(rID);
                if(rater.hasRating(s))
                {
                    count++;
                    sum+= a*rater.getRating(s);
                }
             
            }
            if(count >= minimalRaters)
            {
                av = sum/count;
                res.add(new Rating(s, av));
            }
        }
        Collections.sort(res, Collections.reverseOrder());
        return res;
    }
}
    
    /*

    public double getAverageByID(String id, int minimalRaters)
    {
        RaterDatabase rd = new RaterDatabase();
        rd.initialize("ratings.csv");
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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
    
    private double dotProduct(Rater me, Rater r)
    {
        ArrayList<String> arr = me.getItemsRated();
        double dP = 0;
        for(String s: arr)
        {
            if(r.hasRating(s))
            {
                    double a = me.getRating(me.getID()) - 5;
                    double b = r.getRating(r.getID()) - 5;
                    dP += a*b;
            }
        }
        return dP;
    }
    
    private ArrayList<Rating> getSimilarities(String id)
    {
        ArrayList<Rating> arr = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        
        for(Rater r: RaterDatabase.getRaters())
        {
            if(!r.equals(me))
            {
                double dP = dotProduct(me , r);
                if(dP > 0)
                    arr.add(new Rating(r.getID(), dP));
            }
        }
        Collections.sort(arr, Collections.reverseOrder());
        return arr;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRatings, int minimalRaters)
    {
        ArrayList<Rating> res = new ArrayList<Rating>();
        ArrayList<Rating> arr = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String s: movies)
        {
            double av = 0, sum = 0, count = 0;
            for(int i = 0; i < numSimilarRatings; i++)
            {
                Rating r = arr.get(i);
                double a = r.getValue();
                String rID = r.getItem();
                Rater rater = RaterDatabase.getRater(rID);
                if(rater.hasRating(s))
                {
                    count++;
                    sum+= a*rater.getRating(s);
                }
             
            }
            if(count > minimalRaters)
            {
                av = sum/count;
                res.add(new Rating(s, av));
            }
        }
        Collections.sort(res, Collections.reverseOrder());
        return res;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
    	ArrayList<Rating> res = new ArrayList<Rating>();
    	ArrayList<Rating> list = getSimilarities(id);	
    	ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
	    for (String movieID : movies) {
        	double weightedAverage = 0;
        	double sum = 0;
        	int countRaters = 0;
	    	for (int i = 0; i < numSimilarRaters; i++) {
	    		Rating r = list.get(i);
	    		double weight = r.getValue();
	    		String raterID = r.getItem();
	    		Rater myRater = RaterDatabase.getRater(raterID);
	    		if(myRater.hasRating(movieID)) {
	    			countRaters++;
	    			sum += weight * myRater.getRating(movieID);
	    		}
	    		
	    	}
	    	if (countRaters >= minimalRaters) {
	    		weightedAverage = sum / countRaters;
	    		res.add(new Rating(movieID, weightedAverage));
			}			
	    }
		Collections.sort(res, Collections.reverseOrder());
		return res;	
    }
   /*public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRatings, int minimalRaters, Filter filterCriteria)
    {
        ArrayList<Rating> res = new ArrayList<Rating>();
        ArrayList<Rating> arr = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String s: movies)
        {
            double av = 0, sum = 0, count = 0;
            for(int i = 0; i < numSimilarRatings; i++)
            {
                Rating r = arr.get(i);
                double a = r.getValue();
                String rID = r.getItem();
                Rater rater = RaterDatabase.getRater(rID);
                if(rater.hasRating(s))
                {
                    count++;
                    sum+= a*rater.getRating(s);
                }
             
            }
            if(count >= minimalRaters)
            {
                av = sum/count;
                res.add(new Rating(s, av));
            }
        }
        Collections.sort(res, Collections.reverseOrder());
        return res;
    }*/

