/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class WhichCountriesExport {
    public void listExporters(CSVParser parser) {
        //for each row in the CSV File

        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Value (dollars)");
            String chk = "$999,999,999,999";
            //Check if it contains exportOfInterest
            if ( export.length() > chk.length()){
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.print(country + " ");
                System.out.println(export);
            }
        }

    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser);
    }
}
