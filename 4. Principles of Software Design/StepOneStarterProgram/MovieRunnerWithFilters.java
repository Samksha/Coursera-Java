
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {

    public void printAverageRatings()
    {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv");
        System.out.println("Number of raters: " + sr.getRaterSize());
        System.out.println("Number of movies: " + md.size());
        ArrayList<Rating> rats = sr.getAverageRatings(35);
        Collections.sort(rats);
        for(Rating r: rats)
        {
            System.out.println(r.getValue() + " " + md.getTitle(r.getItem()));
        }
        System.out.println("found " + rats.size() + " movies");
    }
    
    public void printAverageRatingsByYear()
    {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv");
        System.out.println("Number of raters: " + sr.getRaterSize());
        System.out.println("Number of movies: " + md.size());
        YearAfterFilter ya = new YearAfterFilter(2000);
        ArrayList<Rating> rats = sr.getAverageRatingsByFilter(20, ya);
        Collections.sort(rats);
        for(Rating r: rats)
        {
            System.out.println(r.getValue() + " " + md.getYear(r.getItem()) + " " + md.getTitle(r.getItem()));
        }
        System.out.println("found " + rats.size() + " movies");
    }
     
    public void printAverageRatingsByGenre()
    {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv");
        System.out.println("Number of raters: " + sr.getRaterSize());
        System.out.println("Number of movies: " + md.size());
        GenreFilter ya = new GenreFilter("Comedy");
        ArrayList<Rating> rats = sr.getAverageRatingsByFilter(20, ya);
        Collections.sort(rats);
        for(Rating r: rats)
        {
            System.out.println(r.getValue() + " " + md.getTitle(r.getItem()));
            System.out.println("\t" + md.getGenres(r.getItem()));
        }
        System.out.println("found " + rats.size() + " movies");
    }
    
    public void printAverageRatingsByMinutes()
    {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv");
        System.out.println("Number of raters: " + sr.getRaterSize());
        System.out.println("Number of movies: " + md.size());
        MinutesFilter ya = new MinutesFilter(105, 135);
        ArrayList<Rating> rats = sr.getAverageRatingsByFilter(5, ya);
        Collections.sort(rats);
        for(Rating r: rats)
        {
            System.out.println(r.getValue() + " Time: " + md.getMinutes(r.getItem()) + " " + md.getTitle(r.getItem()));
        }
        System.out.println("found " + rats.size() + " movies");
    }
    
    
    public void printAverageRatingsByDirector()
    {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv");
        System.out.println("Number of raters: " + sr.getRaterSize());
        System.out.println("Number of movies: " + md.size());
        DirectorsFilter ya = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> rats = sr.getAverageRatingsByFilter(4, ya);
        Collections.sort(rats);
        for(Rating r: rats)
        {
            System.out.println(r.getValue() + " " + md.getTitle(r.getItem()));
            System.out.println("\t" + md.getDirector(r.getItem()));
        }
        System.out.println("found " + rats.size() + " movies");
    }
    
    public void printAverageRatingsByYearAfterAndGenre()
    {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv");
        System.out.println("Number of raters: " + sr.getRaterSize());
        System.out.println("Number of movies: " + md.size());
        AllFilters af = new AllFilters();
        GenreFilter gf = new GenreFilter("Drama");
        YearAfterFilter ya = new YearAfterFilter(1990);
        af.addFilter(gf);
        af.addFilter(ya);
        ArrayList<Rating> rats = sr.getAverageRatingsByFilter(8, af);
        Collections.sort(rats);
        for(Rating r: rats)
        {
            System.out.println(r.getValue() + " " + md.getYear(r.getItem()) + " " + md.getTitle(r.getItem()));
            System.out.println("\t" + md.getGenres(r.getItem()));
        }
        System.out.println("found " + rats.size() + " movies");
    }
    
    public void printAverageRatingsByDirectorAndMinutes()
    {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv");
        System.out.println("Number of raters: " + sr.getRaterSize());
        System.out.println("Number of movies: " + md.size());
        AllFilters af = new AllFilters();
        DirectorsFilter gf = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        MinutesFilter ya = new MinutesFilter(90, 180);
        af.addFilter(gf);
        af.addFilter(ya);
        ArrayList<Rating> rats = sr.getAverageRatingsByFilter(3, af);
        Collections.sort(rats);
        for(Rating r: rats)
        {
            System.out.println(r.getValue() + " Time: " + md.getMinutes(r.getItem()) + " " + md.getTitle(r.getItem()));
            System.out.println("\t" + md.getDirector(r.getItem()));
        }
        System.out.println("found " + rats.size() + " movies");
    }
}
