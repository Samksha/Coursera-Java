
/**
 * Write a description of testCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class testCipher {

    public String encrypt(String input, int key)
    {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted = alphabet.substring(key) + alphabet.substring(0,key);
        for( int i = 0; i < encrypted.length(); i++)
        {
            char currentChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currentChar);
            if(idx != -1)
            {
                char newChar = shifted.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    
    public int maxIndex(int[] arr)
    {
        int max = 0;
        int index = 0;
        for( int i = 0; i < arr.length; i++)
        {
            if(arr[i] > max)
            {
                max = arr[i];
                index = i;
            }
        }
        return index;
    }
    
    public int countLetters(String message)
    {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i = 0; i < message.length(); i++)
        {
            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alph.indexOf(ch);
            if(dex != -1)
                {
                    counts[dex]++;
                }
        }
        int maxDex = maxIndex(counts);
        return maxDex;
    }
    
    public void test()
    {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int maxDex = countLetters(message);
        System.out.println(maxDex);
        int dkey;
        if(maxDex < 4)
        {
            dkey = 26 + (maxDex-4);
        }
        else
        {
            dkey = maxDex-4;
        }
        System.out.println(dkey);
        String decrypted = encrypt(message, 3);
        System.out.println(decrypted);
    }
}
