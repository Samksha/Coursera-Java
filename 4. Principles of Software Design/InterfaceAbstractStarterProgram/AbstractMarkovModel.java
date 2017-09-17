
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    protected ArrayList<String> getFollows(String key)
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
    
    abstract public String getRandomText(int numChars);

}
