
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class CodonCount
{
    private HashMap<String, Integer> dnaHM;
    public CodonCount()
    {
        dnaHM = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna)
    {
        dnaHM.clear();
        int count = (dna.length() - start)/3;
        for(int i = 0; i < count; i++)
        {
            int currIndex = start+3;
            String codon = dna.substring(start, start+3);
            if(!dnaHM.containsKey(codon))
                dnaHM.put(codon, 1);
            else
            {
                int val = dnaHM.get(codon);
                dnaHM.put(codon, val+1);
            }
            
            start+=3;
        }
    }
    
    public String getMostCommonCodon()
    {
        int max = 0;
        for(Integer i : dnaHM.values())
        {
            if(i > max)
                max = i;
        }
        for(String key : dnaHM.keySet())
        {
            if( dnaHM.get(key).equals(max))
                return key;
        }
        return null;
    }
    
    public void printCodonCounts(int start, int end)
    {
        System.out.println("Count of codons between " + start + " and " + end + " inclusive are: ");
        for(String key : dnaHM.keySet())
        {
            if(dnaHM.get(key) >= start && dnaHM.get(key) <= end)
                {
                    System.out.println(key + " " + dnaHM.get(key));
                }
        }
    }
   
    public void tester()
    {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        //String dna = "CGTTCAAGTTCAA";
        int max = 0;
        for( int i = 0; i < 3; i++)
        {
            
            buildCodonMap(i, dna);
            for(Integer k : dnaHM.values())
            {
                if(k > max)
                max = k;
            }
            System.out.println("Reading frame starting with " + i + " results in " + dnaHM.size() + " unique codons.");
            System.out.println("and most common codon is " + getMostCommonCodon() + " with count " + max);
            printCodonCounts(7, 7);
        }
    }
    
}
