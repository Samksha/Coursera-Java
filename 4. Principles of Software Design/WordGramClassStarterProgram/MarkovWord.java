
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWord implements IMarkovModel 
{
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int ord) {
        myRandom = new Random();
        myOrder = ord;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    private int indexOf(String[] words, WordGram target, int start)
    {
        for(int i = start; i < words.length-myOrder; i++)
        {
            WordGram w = new WordGram(words, i, myOrder);
            if(w.equals(target)) return i;
        }
        return -1;
    }
    
    /*public void testIndexOf()
    {
        String[] words = {"this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test"};
        System.out.println(indexOf(words, "this", 0));
        System.out.println(indexOf(words, "this", 3));
        System.out.println(indexOf(words, "frog", 0));
        System.out.println(indexOf(words, "simple", 2));        
    }*/
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText, index, myOrder);
        //String key = myText[index];
        sb.append(kGram.toString());
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(kGram);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
        }
        
        /*System.out.println(key);
        ArrayList<String> a = getFollows(key);
        for(String s : a)
        {
            System.out.println(s);
        }*/
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        for(int n = indexOf(myText, kGram, 0); n != -1;)
        {
            //int n = indexOf(myText, key, i);
            follows.add(myText[n+myOrder]);
            int x = n;
            n = indexOf(myText, kGram, x+1);   
        }
        return follows;
    }
}
