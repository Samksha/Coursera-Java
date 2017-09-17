
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
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
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 1);
        String key = myText.substring(index, index+1);
        sb.append(key);

        for (int k = 0; k < numChars - 1; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            String followsRandom = follows.get(myRandom.nextInt(follows.size()));
            sb.append(followsRandom);
            key = followsRandom;
        }
        
        return sb.toString();
    }
}

