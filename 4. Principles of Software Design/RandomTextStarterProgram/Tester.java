
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Tester {

    public void testGetFollows()
    {
        MarkovOne mark = new MarkovOne();
        mark.setTraining("this is a test yes this is a test.");
        ArrayList<String> ans = mark.getFollows("es");
        for(String s: ans)
        {
            System.out.println(s);
        }
        System.out.println("Size = " + ans.size());
    }
    
    public void testGetFollowsWithFile()
    {
        FileResource fr = new FileResource();
        String s = fr.asString();
        MarkovOne mark = new MarkovOne();
        mark.setTraining(s);
        ArrayList<String> ans = mark.getFollows("he");
        System.out.println("Size = " + ans.size());        
    }
}
