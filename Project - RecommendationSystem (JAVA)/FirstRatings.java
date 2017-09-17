
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String filename)
    {
        ArrayList<Movie> arr = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord rec: parser)
        {
            Movie m = new Movie(rec.get("id"), rec.get("title"), rec.get("year"), rec.get("genre"),
            rec.get("director"), rec.get("country"), rec.get("poster"), Integer.parseInt(rec.get("minutes")));
            arr.add(m);
        }
        return arr;
    }

    public void testLoadMovies()
    {
        ArrayList<Movie> arr = loadMovies("data/ratedmoviesfull.csv");
        /* Print Titles:
         * 
         * for(Movie m : arr)
        {
            System.out.println(m.getTitle());
        }*/
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        String maxDir = new String();
        int maxValueInMap = 0;
        int c_min = 0, c_genre = 0;
        for(Movie m : arr)
        {
            if(m.getMinutes() > 150)
            {
                c_min++;
            }
            
            String s = m.getGenres();
            String gen[] = s.split(", ");
            for(int j = 0; j < gen.length; j++)
            {
                if(gen[j].equals("Comedy")) c_genre++;
            }
            /*
            if(j != -1 && m.getGenres().substring(j, j+6).equals("Comedy"))
            {
                c_genre++;
            }*/
            
            String s1 = m.getDirector();
            String dir[] = s1.split(", ");
            for(int i = 0; i < dir.length; i++)
            {
                //System.out.print(dir[i] + "-");
                if(!map.containsKey(dir[i]))
                {
                    map.put(dir[i], 1);
                }
                else
                {
                    int a = map.get(dir[i]);
                    map.put(dir[i], a+1);
                }
            }
        }
        maxValueInMap=(Collections.max(map.values()));
            for (String s : map.keySet()) 
            { 
                if (map.get(s)==maxValueInMap) 
                {
                    System.out.println("DirectorWithMax: " + s);
                }
                //System.out.println(s);
            }
        System.out.println("Movies read = " + arr.size());
        System.out.println("Minutes > 150 = " + c_min);
        System.out.println("Genre (Comedy) = " + c_genre);
        System.out.println("Maximum: " + maxValueInMap);
    }
    
    public ArrayList<Rater> loadRaters(String filename)
    {
        ArrayList<Rater> arr = new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        int i = 0;
        for(CSVRecord rec: parser)
        {
            if(i == 0)
            {
                Rater r = new EfficientRater(rec.get("rater_id"));
                r.addRating(rec.get("movie_id"), Integer.parseInt(rec.get("rating"))); 
                arr.add(r);        
                i++;
            }
            else if(arr.get(i-1).getID().equals(rec.get("rater_id")))
            {
                arr.get(i-1).addRating(rec.get("movie_id"), Integer.parseInt(rec.get("rating")));
            }
            else
            {
                Rater r = new EfficientRater(rec.get("rater_id"));
                r.addRating(rec.get("movie_id"), Integer.parseInt(rec.get("rating"))); 
                arr.add(r);   
                i++;
            }
        }
        return arr;
    }
    
    public void testLoadRaters()
    {
        ArrayList<Rater> arr = loadRaters("data/ratings.csv");
        String testID = "193", itemtofind = "1798709";
        int maxratings = 0, count = 0;
        ArrayList<String> diff_items = new ArrayList<String>();
        numRatingsByRater(arr, testID);
        MaxRatings(arr, maxratings);
        
        int a = numRatingsByMovie(arr, itemtofind, count);
        System.out.println("Number of ratings for " + itemtofind + " : " + a);
        
        diff_items = numDiffItems(arr, diff_items);
        System.out.println("Number of different movies : " + diff_items.size());
        
        System.out.println("Raters read = " + arr.size());
        
    }
    
    //Helper methods for Rater
    
    public void numRatingsByRater(ArrayList<Rater> arr, String testID)
    {
        for(Rater r: arr)
        {
        if(r.getID().equals(testID))
            {
                System.out.println("For rater " + testID + " ratings = " + r.numRatings());
            }
        }
    }
    
    public void MaxRatings(ArrayList<Rater> arr, int maxratings)
    {
        for(Rater r : arr)
        {
            if(r.numRatings() > maxratings) maxratings = r.numRatings();
        }
        System.out.println("Maximum ratings : " + maxratings);
        for(Rater r : arr)
        {
            if(r.numRatings() == maxratings) System.out.println("Rater with max ratings : " + r.getID());
        }
    }
    
    public int numRatingsByMovie(ArrayList<Rater> arr, String itemtofind, int count)
    {
        for(Rater r : arr)
        {
            if(r.hasRating(itemtofind))
            {
                count++;
            }
        }
        return count;
    }
    
    public ArrayList<String> numDiffItems(ArrayList<Rater> arr, ArrayList<String> diff_items)
    {
        for(Rater r : arr)
        {           
            ArrayList<String> items = r.getItemsRated();
            for(String s : items)
            {
                if(!diff_items.contains(s)) diff_items.add(s);
            }
        }
        return diff_items;
    }
}
