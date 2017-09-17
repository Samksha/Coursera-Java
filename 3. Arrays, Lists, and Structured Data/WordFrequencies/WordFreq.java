
/**
 * Write a description of WordFreq here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class WordFreq {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs; 
    
    public WordFreq()
    {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique()
    {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        
        for(String word : fr.words())
        {
            word = word.toLowerCase();
            int idx = myWords.indexOf(word);
            if(!myWords.contains(word))
            {
                myWords.add(word);
                myFreqs.add(1);
            }
            else
            {
                int freq = myFreqs.get(idx);
                myFreqs.set(idx, freq+1);
            }
        }
    }
    
    public int findIndexOfMax()
    {
        int max = 0;
        int idx = 0;
        for(int i = 0; i < myFreqs.size(); i++)
        {
            if(myFreqs.get(i) > max)
            {
                max = myFreqs.get(i);
                idx = i;
            }
        }
        return idx;
    }
    
    public void tester()
    {
        findUnique();
        System.out.println("No. of Unique Words: " + myWords.size());
        /*for(int i = 0; i < myWords.size(); i++)
        {
            System.out.println( myWords.get(i) + "\t" + myFreqs.get(i));
        }*/
        int x = findIndexOfMax();
        System.out.println( "MaxIndex = " + myWords.get(x) + "\t" + myFreqs.get(x));
    }
    
}
