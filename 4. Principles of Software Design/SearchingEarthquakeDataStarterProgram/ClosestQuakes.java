
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // TO DO
        int n = quakeData.size();
            //QuakeEntry entry = quakeData.get(k);
            //double dist = entry.getLocation().distanceTo(current);    
            for(int i = 0; i < n; i++)
            {
                for(int j = 0; j < n-1; j++)
                    {
                        if(quakeData.get(j).getLocation().distanceTo(current) > quakeData.get(j+1).getLocation().distanceTo(current))
                        {
                            QuakeEntry t = quakeData.get(j+1);
                            quakeData.set(j+1, quakeData.get(j));
                            quakeData.set(j, t);
                        }
                    }
            }
        
        for(int i = 0; i < howMany; i++)
        {
            ret.add(quakeData.get(i));
        }
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
