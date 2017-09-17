
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class CharactersInPlay 
{
    private ArrayList<String> listChars;
    private ArrayList<Integer> listCount;
    
    public CharactersInPlay()
    {
        listChars = new ArrayList<String>();
        listCount = new ArrayList<Integer>();
    }
    
    public void update(String person)
    {
        int idx = listChars.indexOf(person);
        if(!listChars.contains(person))
        {
            listChars.add(person);
            listCount.add(1);
        }
        else
        {
            int cnt = listCount.get(idx);
            listCount.set(idx, cnt+1);
        }
    }
    
    public void findAllCharacters()
    {
        FileResource fr = new FileResource();
        for(String s : fr.lines())
        {
            int index = s.indexOf(".");
            if(index != -1)
            {
                String person = s.substring(0, index);
                update(person);
            }
        }
    }
      
    public void charactersWithNumParts (int num1, int num2)
    {
        for( int i = 0; i < listChars.size(); i++)
        {
            if(listCount.get(i) >= num1 && listCount.get(i) <= num2)
                 System.out.println(listChars.get(i) + "\t" + listCount.get(i));
        }
    }
    
    public void tester()
    {
        findAllCharacters();
        for(int i= 0 ; i < listChars.size(); i++)
        {
            if(listCount.get(i) > 50)
                System.out.println(listChars.get(i) + "\t" + listCount.get(i));
        }
    }
    
}
