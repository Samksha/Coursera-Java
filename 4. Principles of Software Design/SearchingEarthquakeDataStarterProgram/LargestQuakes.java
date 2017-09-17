
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class LargestQuakes {
    
    public int indexOfLargest(ArrayList<QuakeEntry> data)
    {
        double max = 0;
        int index = 0;
        for(int i = 0; i < data.size(); i++)
        {
            QuakeEntry temp = data.get(i);
            if(temp.getMagnitude() > max)
                {
                    max = temp.getMagnitude();
                    index = i;
                }
        }
        return index;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int HowMany)
    {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for(int i = 0; i < quakeData.size(); i++)
        {
            for(int j = 0; j < quakeData.size()-1; j++)
            {
                if(quakeData.get(j).getMagnitude()<quakeData.get(j+1).getMagnitude())
                {
                    QuakeEntry t = quakeData.get(j+1);
                    quakeData.set(j+1, quakeData.get(j));
                    quakeData.set(j, t);
                }
            }
        }
        for( int i = 0; i < HowMany; i++)
        {
            ret.add(quakeData.get(i));
        }
        return ret;
    }
    
    public void findLargestQuakes()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read " + list.size() + " quakes");
        ArrayList<QuakeEntry> largestList = getLargest(list, 50);
        for(QuakeEntry entry: largestList)
        {
            System.out.println(entry.toString());
        }
        System.out.println("number found: " + largestList.size());
    }

}
