
/**
 * Write a description of CountCommon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CountCommon 
{   
    public void test()
    {
        FileResource fr = new FileResource();
        //String[] common = new String[100];
        int[] cnt = new int[20];
        int index = 1;
        for(String s : fr.words())
        {
            int m = s.length();
            cnt[m]++;
        }
        int max = 0;
        for( int i = 0; i < 20; i++)
        {
            if( cnt[i] > max)
            {
                max = cnt[i];
                index = i;
            }
        }
        
        System.out.println("Most common word length = " + index);
    }
    
}
