
/**
 * Write a description of Cipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Cipher {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private String shiftedAlpha1;
    private String shiftedAlpha2;
    public Cipher(int key1, int key2)
    {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlpha1 = alphabet.substring(26 - key1) + alphabet.substring(0, 26 - key1);
        shiftedAlpha2 = alphabet.substring(26 - key2) + alphabet.substring(0, 26 - key2);
    }
    
    public String encrypt(String input)
    {
        StringBuilder encrypted = new StringBuilder(input);
        int r = 0;
        for( int i = 0; i < input.length(); i++)
        {
            char currentChar = Character.toLowerCase(encrypted.charAt(i));           
            int idx = alphabet.indexOf(currentChar);
            if(idx  != -1)
            {
                r++;
                if(r%2 == 0)
                { 
                    char newChar = shiftedAlphabet1.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
                else
                {
                    char newChar = shiftedAlphabet2.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
                
            }         
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input)
    {        
        
        //shiftedAlpha1 = alphabet.substring(key1) + alphabet.substring(0, 26 - key1);
        //shiftedAlpha2 = alphabet.substring(key2) + alphabet.substring(0, 26 - key2);
        StringBuilder decrypted = new StringBuilder(input);
        int r = 0;
        for( int i = 0; i < input.length(); i++)
        {
            char currentChar = Character.toLowerCase(decrypted.charAt(i));           
            int idx = alphabet.indexOf(currentChar);
            if(idx != -1)
            {
               
                if(r%2 == 0)
                {
                    char newChar = shiftedAlpha1.charAt(idx);
                    decrypted.setCharAt(i, newChar);                   
                }
                else
                {
                    char newChar = shiftedAlpha2.charAt(idx);
                    decrypted.setCharAt(i, newChar);
                }
                r++;
            }
        }
        return decrypted.toString();
    }
    
    public void test()
    {
        //int key = 15;
        //FileResource fr = new FileResource();
        //String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        //fr.asString();
        String message = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        String encrypted = decrypt(message);
        System.out.println(encrypted);
    }
}
