import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry temp : quakeData)
        {
            if(temp.getMagnitude() > magMin)
                answer.add(temp);
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry temp: quakeData)
        {
            System.out.println(temp.getLocation().distanceTo(from));
            if(temp.getLocation().distanceTo(from) < distMax)
                answer.add(temp);
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> magFilter = filterByMagnitude(list, 5.0);
        for(QuakeEntry temp: magFilter)
        {
            System.out.println(temp.toString());
        }
        System.out.println("Found " +magFilter.size()+" quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        
        ArrayList<QuakeEntry> distFilter = filterByDistanceFrom(list, 1000000, city);
        for(QuakeEntry temp: distFilter)
        {
            System.out.println(temp.getLocation().distanceTo(city) + " " + temp.getInfo());
        }
        System.out.println("Found "+distFilter.size()+" quakes that match the criteria"); 

        // TODO
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry temp: quakeData)
        {
            if(temp.getDepth() > minDepth && temp.getDepth() < maxDepth)
            {
                answer.add(temp);
            }
        }
        return answer;
    }
    
    public void quakesOfDepth()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read "+list.size()+" quakes");
        ArrayList<QuakeEntry> depFilter = filterByDepth(list, -4000, -2000);
        for(QuakeEntry temp: depFilter)
        {
            System.out.println(temp.toString());
        }
        System.out.println("Found "+depFilter.size()+" quakes that match the criteria");
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry temp: quakeData)
        {
            String s = temp.getInfo();
            int n = temp.getInfo().length();
            //System.out.println(s.substring(n-phrase.length(), n));
            if(where.equals("end"))
            {
                
                if(s.substring(n-phrase.length(), n).equals(phrase))
                    answer.add(temp);
            }
            else if(where.equals("start"))
            {
                if(s.substring(0, phrase.length()).equals(phrase))
                    answer.add(temp);
            }
            else if(where.equals("any"))
            {
                for(int i = 0; i < n-phrase.length(); i++)
                {
                    if(temp.getInfo().substring(i, i+phrase.length()).equals(phrase))
                        answer.add(temp);
                }
            }
        }
        
        return answer;
    }
    
    public void quakesByPhrase()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read "+list.size()+" quakes");
        ArrayList<QuakeEntry> phrFilter = filterByPhrase(list, "any", "Can");
        for(QuakeEntry temp: phrFilter)
        {
            System.out.println(temp.toString());
        }
        System.out.println("Found "+phrFilter.size()+" quakes that match the criteria");
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
