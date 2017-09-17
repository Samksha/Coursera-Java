
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


import java.util.*;
import edu.duke.*;
import java.io.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
  
    private int N;
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int a) {
        myRandom = new Random();
        N = a;
        map = new HashMap<String, ArrayList<String>>();
    }
    
    private String getKey(int index)
    {
        return myText.substring(index, index + N);
    }
    
    private String getFollowingLetter(int index)
    {
        return myText.substring(index+N, index+N+1);
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setN(int a)
    {
        N = a;
    }
    
    private void buildMap() {

        for (int i = 0; i < myText.length() - N; i++) 
        {
            
            String key = getKey(i);
            String follow = getFollowingLetter(i);
            
            if (map.containsKey(key)) {
                map.get(key).add(follow);
            }
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.   add(follow);
                map.put(key, list);
            }
        }

    }

    @Override
    public ArrayList<String> getFollows(String key)
    {
        return map.get(key);
    }
    
    @Override
    public void setTraining(String s){
        myText = s.trim();
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
    
    public void printHashMapInfo() {
		System.out.printf("Map size:\t%d\nMax size:\t%d\n", mapSize(), longestFollowsSize());
//		for (String key : map.keySet()) {
//			System.out.printf("Key:\t[%s]\tvalues: ", key);
//			System.out.println(map.get(key));
//		}

	}

	public int mapSize() {
		return map.size();
	}

	public int longestFollowsSize() {
		int maxSize = 0;
		for (String key : map.keySet()) {
			maxSize = Math.max(maxSize, map.get(key).size());
		}

		return maxSize;
	}

    public String toString()
    {
        String s = "MarkovModel of number " + N;
        return s;
    }
}


