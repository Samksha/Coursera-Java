
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int N;
    
    public MarkovModel() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setN(int a)
    {
        N = a;
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public ArrayList<String> getFollows(String key)
    {
        ArrayList<String> ans = new ArrayList<String>();
        int n = myText.length();
        for(int i = 0; i < n-key.length(); i++)
        { 
            int j = i+key.length();
            String s = myText.substring(i,j);
            if(s.equals(key))
            {
                ans.add(myText.substring(j, j+1));
            }
        }
        return ans;
    }
    
     public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index, index+N);
        sb.append(key);
        
        for(int k=0; k < numChars-N; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
}


