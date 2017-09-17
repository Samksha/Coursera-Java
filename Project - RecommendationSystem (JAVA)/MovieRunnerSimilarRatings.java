
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings()
    {
        FourthRatings sr = new FourthRatings();
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv");
        RaterDatabase rd = new RaterDatabase();
        rd.initialize("ratings.csv");
        System.out.println("Number of raters: " + rd.size());
        System.out.println("Number of movies: " + md.size());
        ArrayList<Rating> rats = sr.getAverageRatings(35);
        Collections.sort(rats);
        for(Rating r: rats)
        {
            System.out.println(r.getValue() + " " + md.getTitle(r.getItem()));
        }
        System.out.println("found " + rats.size() + " movies");
    }
    
    public void printAverageRatingsByYearAfterAndGenre()
    {
        FourthRatings sr = new FourthRatings();
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv");
        RaterDatabase rd = new RaterDatabase();
        rd.initialize("ratings.csv");
        System.out.println("Number of raters: " + rd.size());
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

    public void printSimilarRatings()
    {
        FourthRatings sr = new FourthRatings();
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv");
        RaterDatabase rd = new RaterDatabase();
        rd.initialize("ratings.csv");
        System.out.println("Number of raters: " + rd.size());
        System.out.println("Number of movies: " + md.size());
        ArrayList<Rating> rats = sr.getSimilarRatings("337", 10, 3);
        System.out.println("found " + rats.size() + " movies");
        for(Rating r: rats)
        {
            System.out.println(r.getValue() + " " + md.getTitle(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre()
    {
        FourthRatings sr = new FourthRatings();
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv");
        RaterDatabase rd = new RaterDatabase();
        rd.initialize("ratings.csv");
        System.out.println("Number of raters: " + rd.size());
        System.out.println("Number of movies: " + md.size());
        GenreFilter gf = new GenreFilter("Mystery");
        ArrayList<Rating> rats = sr.getSimilarRatingsByFilter("964", 20, 5, gf);
        System.out.println("found " + rats.size() + " movies");
        for(Rating r: rats)
        {
            System.out.println(r.getValue() + " " + md.getTitle(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector()
    {
        FourthRatings sr = new FourthRatings();
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv");
        RaterDatabase rd = new RaterDatabase();
        rd.initialize("ratings.csv");
        System.out.println("Number of raters: " + rd.size());
        System.out.println("Number of movies: " + md.size());
        DirectorsFilter gf = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> rats = sr.getSimilarRatingsByFilter("120", 10, 2, gf);
        System.out.println("found " + rats.size() + " movies");
        for(Rating r: rats)
        {
            System.out.println(r.getValue() + " " + md.getTitle(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes()
    {
        FourthRatings sr = new FourthRatings();
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv");
        RaterDatabase rd = new RaterDatabase();
        rd.initialize("ratings.csv");
        System.out.println("Number of raters: " + rd.size());
        System.out.println("Number of movies: " + md.size());
        AllFilters af = new AllFilters();
        GenreFilter gf = new GenreFilter("Drama");
        MinutesFilter mf = new MinutesFilter(80, 160);
        af.addFilter(gf);
        af.addFilter(mf);
        ArrayList<Rating> rats = sr.getSimilarRatingsByFilter("168", 10, 3, af);
        System.out.println("found " + rats.size() + " movies");
        for(Rating r: rats)
        {
            System.out.println(r.getValue() + " " + md.getTitle(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes()
    {
        FourthRatings sr = new FourthRatings();
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv");
        RaterDatabase rd = new RaterDatabase();
        rd.initialize("ratings.csv");
        System.out.println("Number of raters: " + rd.size());
        System.out.println("Number of movies: " + md.size());
        AllFilters af = new AllFilters();
        YearAfterFilter gf = new YearAfterFilter(1975);
        MinutesFilter mf = new MinutesFilter(70, 200);
        af.addFilter(gf);
        af.addFilter(mf);
        ArrayList<Rating> rats = sr.getSimilarRatingsByFilter("314", 10, 5, af);
        System.out.println("found " + rats.size() + " movies");
        for(Rating r: rats)
        {
            System.out.println(r.getValue() + " " + md.getTitle(r.getItem()));
        }
    }
}
