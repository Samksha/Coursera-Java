
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String,ArrayList<String>> wordsHashMap;
    
    public WordsInFiles()
    {
        wordsHashMap = new HashMap<String,ArrayList<String>>();
    }
    
    private void addWordsFromFile (File f)
    {
        FileResource fr = new FileResource(f);
        for(String word : fr.words())
        {
            if(!wordsHashMap.containsKey(word))
            {
                ArrayList<String> list = new ArrayList();;
                list.add(f.getName());
                wordsHashMap.put(word, list);
            }
            else
            {
                ArrayList<String> list = wordsHashMap.get(word);
                if(!list.contains(f.getName()))
                {
                    list.add(f.getName());
                    wordsHashMap.put(word, list);
                }
            }
        }
    }
    
    public void buildWordFileMap()
    {
        wordsHashMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for( File f : dr.selectedFiles())
        {
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber()
    {
        int max = 0;
        for( String word : wordsHashMap.keySet())
        {
            int x = wordsHashMap.get(word).size();
            if(x > max)
                max = x;
        }
        return max;
    }
    
    public void wordsInNumFiles(int number)
    {
        int count = 0;
        for(String word : wordsHashMap.keySet())
        {
            int x = wordsHashMap.get(word).size();
            if(x == number)
            {
                //System.out.print(word + " ");
                count++;
            }
        }
        System.out.println("Number = " + count);
    }
    
    public void printFilesIn(String word)
    {
         ArrayList<String> list = wordsHashMap.get(word);
         System.out.println(word + " appears in: ");
         for(String s : list)
         {
             System.out.println(s);
         }
    }
    
    public void tester()
    {
        buildWordFileMap();
        System.out.println("The greatest number of files a word appears in is " + maxNumber() + " and the words are ");
        wordsInNumFiles(7);
        System.out.println("");
        printFilesIn("tree");
        
    }
    
}
