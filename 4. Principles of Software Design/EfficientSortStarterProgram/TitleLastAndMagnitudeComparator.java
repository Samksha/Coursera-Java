
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2)
    {
        String t = qe1.getInfo().substring(qe1.getInfo().lastIndexOf(" "));
        if(qe1.getInfo().substring(qe1.getInfo().lastIndexOf(" ") +1).compareTo(qe2.getInfo().substring(qe2.getInfo().lastIndexOf(" ") +1)) < 0)
        {
            return -1;
        }
        if(qe1.getInfo().substring(qe1.getInfo().lastIndexOf(" ") +1).compareTo(qe2.getInfo().substring(qe2.getInfo().lastIndexOf(" ") +1)) > 0)
        {
            return 1;
        }
        if(qe1.getInfo().substring(qe1.getInfo().lastIndexOf(" ") +1).compareTo(qe2.getInfo().substring(qe2.getInfo().lastIndexOf(" ") +1)) == 0)
        {
                    return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        }

        return 0;
    }
    
}
