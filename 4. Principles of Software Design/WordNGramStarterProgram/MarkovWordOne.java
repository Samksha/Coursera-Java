
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    private int indexOf(String[] words, String target, int start)
    {
        for(int i = start; i < words.length; i++)
        {
            if(words[i].equals(target))
            {
                return i;
            }
        }
        return -1;
    }
    
    public void testIndexOf()
    {
        String[] words = {"this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test"};
        System.out.println(indexOf(words, "this", 0));
        System.out.println(indexOf(words, "this", 3));
        System.out.println(indexOf(words, "frog", 0));
        System.out.println(indexOf(words, "simple", 2));        
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        
        /*System.out.println(key);
        ArrayList<String> a = getFollows(key);
        for(String s : a)
        {
            System.out.println(s);
        }*/
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        for(int n = indexOf(myText, key, 0); n != -1;)
        {
            //int n = indexOf(myText, key, i);
            follows.add(myText[n+1]);
            int x = n;
            n = indexOf(myText, key, x+1);   
        }
        return follows;
    }

}


