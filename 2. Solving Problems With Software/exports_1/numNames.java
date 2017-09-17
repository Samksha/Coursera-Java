
/**
 * Write a description of numNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class numNames {
    
    public void totalNames()
    {
        int g = 1;
        int b= 1;
        int sus = 0;
        FileResource fr1 = new FileResource();
        for(CSVRecord rec : fr1.getCSVParser(false))
        {
            if("Owen".equals(rec.get(0)))
            {
                break;
            }
            else
            {
                g++;
            }
        }
        //System.out.println("g = " +g);
        FileResource fr2 = new FileResource("yob2014.csv");
        for(CSVRecord rec2 : fr2.getCSVParser(false))
        {
           
            if(b == g)
            System.out.println("RANK = " + g + " New Name = " + rec2.get(0));
            b++;
        }
        
        //System.out.println("Name = " + b.get(0));
        //System.out.println("Boys = " + b);       
    }
    
    public double find( FileResource fr1)
    {
        double g= 1;
        //double sum = 0;
        for( CSVRecord rec : fr1.getCSVParser(false))
        {
            if("Mich".equals(rec.get(0)) && rec.get(1).equals("M"))
            {
                break;
            }
            else if(rec.get(1).equals("M"))
            {
                //sum += Double.parseDouble(rec.get(2));
                g++;
            }
            g = 9999999;
        }
        return g;
    }
    
    public void highestRank()
    {
        DirectoryResource dr = new DirectoryResource();
        double high = 999990;
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            double g = find(fr);
            if(g < high)
                {
                    high = g;
                }
        }
        System.out.println("RANK = " + high);
        int c = 1880;
        for(File f1 : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f1);
            c++;
            double g = find(fr);
            if(g == high)
                break;
        }
        System.out.println("YEAR = " + c);
    }  
        
    public void ave()
    {
        double a = 0, c= 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            a += find(fr);
            c++;
        }       
        double av = a/c;
        System.out.println("AVE = " + av);
    }
    
    public void rankmaster()
    {
        FileResource fr = new FileResource();
        double x = find(fr);
        System.out.println("CHANGE = " + --x);
    }
 }

