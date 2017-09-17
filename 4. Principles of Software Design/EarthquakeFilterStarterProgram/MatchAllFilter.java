
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class MatchAllFilter implements Filter 
{
    private ArrayList<Filter> fil;
    public MatchAllFilter()
    {
        fil = new ArrayList<Filter>();
    }
    
    void addFilter(Filter f)
    {
        fil.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe)
    {
        for(Filter f: fil)
        {
            if(!f.satisfies(qe))
                return false;
        }
        return true;
    }
    
    
   
}
