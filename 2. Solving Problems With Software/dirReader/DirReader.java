/**
 * Print the names of all files selected within a directory (or folder).
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.File;

public class DirReader {
    public void checkDir() {
        URLResource dr = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String f : dr.lines()) {
            System.out.println(f);
        }
    }
}
