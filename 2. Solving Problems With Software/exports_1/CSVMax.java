
/**
 * Write a description of CSVMax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser)
    {
        CSVRecord largestSoFar = null;
        for( CSVRecord currentRow : parser)
        {
            if( largestSoFar == null)
            { 
                largestSoFar = currentRow;
            }
            else
            {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                if(currentTemp > Double.parseDouble(largestSoFar.get("TemperatureF")))
                    largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }
    
    public void test()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord rec = hottestHourInFile(parser);
        System.out.println(rec.get("TemperatureF"));
    }
    
    public void testmore()
    {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord largestSoFar = null;
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord current = hottestHourInFile(parser);
            if(largestSoFar == null)
            {
                largestSoFar = current;
            }
            else
            {
                if(Double.parseDouble(largestSoFar.get("TemperatureF")) < Double.parseDouble(current.get("TemperatureF")))
                {
                    largestSoFar = current;
                }
            }
        }
        System.out.println(largestSoFar.get("TemperatureF"));
    }
}
