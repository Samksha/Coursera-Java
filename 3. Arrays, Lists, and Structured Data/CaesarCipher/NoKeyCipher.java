
/**
 * Write a description of NoKeyCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class NoKeyCipher 
{
    private String alphabet;
    public NoKeyCipher()
    {     
        alphabet = "abcdefghijklmnopqrstuvwxyz";
    }
 
    public String decrypt(String input, int key1, int key2)
    {   
        String shift1 = alphabet.substring(26 - key1) + alphabet.substring(0, 26 - key1);
        String shift2 = alphabet.substring(26 - key2) + alphabet.substring(0, 26 - key2);
        StringBuilder decrypted = new StringBuilder(input);
        int letter = 0;
        for( int i = 0; i < input.length(); i++)
        {
            char currC = Character.toLowerCase(input.charAt(i));
            int index = alphabet.indexOf(currC);
            if(index != -1)
            {
                if(i%2 == 0)
                {
                    char newC = shift1.charAt(index);
                    decrypted.setCharAt(i, newC);
                }
                else
                {
                    char newC = shift2.charAt(index);
                    decrypted.setCharAt(i, newC);
                }
                letter++;
            }
        }
        return decrypted.toString();
    }
    
    public int findKey1(String input)
    {
        int[] count = new int[26];
        for(int i = 0; i < input.length(); i = i+2)
        {
            char c = input.charAt(i);
            int idx = alphabet.indexOf(c);
            if(idx != -1)
            {
                count[idx]+=1;
            }
        }
        
        int max = 0, pos = 0;
        for( int i = 0; i < 26; i++)
        {
            if(count[i] > max)
            {
                max = count[i];
                pos = i;
            }   
        }
        
        int key = pos - 4;
        
        return key;
    }
    
    public int findKey2(String input)
    {
        int[] count = new int[26];
        for(int i = 1; i < input.length(); i = i+2)
        {
            char c = input.charAt(i);
            int idx = alphabet.indexOf(c);
            if(idx != -1)
            {
                count[idx]++;
            }
        }
        
        int max = 0, pos = 0;
        for( int i = 0; i < 26; i++)
        {
            if(count[i] > max)
            {
                max = count[i];
                pos = i;
            }   
        }
        
        int key = pos - 4;
        
        return key;
    }
    
    public void test()
    {
        //String input = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        FileResource fr = new FileResource();
        String input = fr.asString();
        int key1 = findKey1(input);
        int key2 = findKey2(input);
        String decrypted = decrypt(input, key1, key2);
        //System.out.println(alphabet);
        //System.out.println(shift1);
        //System.out.println(shift2);
        //System.out.println(input);
        System.out.println(decrypted);
        System.out.println("Key1 = " + key1 + " Key2 = " + key2);
    }
}
