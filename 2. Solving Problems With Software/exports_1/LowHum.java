
/**
 * Write a description of LowHum here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class LowHum {
    
    public CSVRecord lowestOfTwo(CSVRecord rec, CSVRecord lowestSoFar)
    {
        if(lowestSoFar == null)
            {
                lowestSoFar = rec;
            }
            else
            {
                if(Double.parseDouble(rec.get("Humidity")) < Double.parseDouble(lowestSoFar.get("Humidity")))
                    lowestSoFar = rec;
            }
            return lowestSoFar;
    }
    
    public CSVRecord lowHumInFile(CSVParser parser)
    {
        CSVRecord lowestSoFar = null;
        for(CSVRecord rec : parser)
        {
            lowestSoFar = lowestOfTwo(rec, lowestSoFar);
        }        
        return lowestSoFar;
    }
    
    public void test()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord rec = lowHumInFile(parser);
        System.out.println(rec.get("DateUTC"));
    }
    
    public void testall()
    {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestOfAll = null;
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord rec = lowHumInFile(parser);
            lowestOfAll = lowestOfTwo(rec, lowestOfAll);
        }
        System.out.println(lowestOfAll.get("DateUTC"));
    }
    
    public void ave()
    {
        DirectoryResource dr = new DirectoryResource();
        double a = 0, c= 0;
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            for(CSVRecord rec : parser)
            {
            a += Double.parseDouble(rec.get("TemperatureF"));
            c++;
            }
        }
        double av = a/c;
        System.out.println(av);
    }
    
    public void ave2()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double a = 0, c = 0;
        for(CSVRecord rec : parser)
        {
            if(Double.parseDouble(rec.get("Humidity")) >= 80 )
            {
                a += Double.parseDouble(rec.get("TemperatureF"));
                c++;
            }
        }
        double av = a/c;
        System.out.println(av);
    }
    
    public void coldInFile()
    {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldest = null;
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            for(CSVRecord rec : parser)
            {
                if(coldest == null)
                {
                    coldest = rec;
                }
                else
                {
                    if(Double.parseDouble(rec.get("TemperatureF")) < Double.parseDouble(coldest.get("TemperatureF")))
                    {
                        coldest = rec;
                    }
                }
            }         
        }
        System.out.println(coldest.get("TemperatureF"));
    }
}
