import java.util.*;
import java.io.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder s = new StringBuilder(message);
        StringBuilder sliced = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i+=totalSlices)
        {
            sliced.append(s.charAt(i));
        }
        return sliced.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for( int i = 0; i < klength; i++)
        {
            String s = sliceString(encrypted, i, klength);
            //cc.decrypt(sliceString);
            key[i] = cc.getKey(s);
            //System.out.println(key[i]);
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet<String> set = new HashSet<String>();
        for(String s : fr.lines())
        {
            s.toLowerCase();
            set.add(s);
        }
        return set;
    }
    
    public int countWords(String message, HashSet<String> dictionary)
    {
        int count = 0;
        String[] split = message.split("\\W+");
        for( int i = 0; i < split.length; i++)
        {
            if(dictionary.contains(split[i]))
                count++;
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary)
    {
        int max = 0;
        char mostCommon = mostCommonCharIn(dictionary);
        for( int i = 1; i < 100; i++)
        {
            int []key = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String message = vc.decrypt(encrypted);
            int x = countWords(message, dictionary);
            if(x > max)
                {
                    max = x;
                }   
        }
        for( int i = 1; i < 100; i++)
        {
            int []key = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String message = vc.decrypt(encrypted);
            int x = countWords(message, dictionary);
            if(x == max)
                {
                    return message;
                }   
        }
        return null;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder al = new StringBuilder(alphabet);
        int[] countAlph = new int[26];
        for(String s : dictionary)
        {
            StringBuilder sb = new StringBuilder(s);
            for(int i = 0; i < s.length(); i++)
            {
                int x = alphabet.indexOf(sb.charAt(i));
                if(x != -1)
                    countAlph[x]++;
            }
        }
        int index = 0, max = 0;
        for( int i = 0; i < 26; i++)
        {
            if(countAlph[i] > max)
                {
                    max = countAlph[i];
                    index = i;
                }
        }
        return al.charAt(index);
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages)
    {
        int max = 0;
        for(String lan : languages.keySet())
        {
            String broken = breakForLanguage(encrypted, languages.get(lan));
            int x = countWords(broken, languages.get(lan));
            if(max < x)
                max = x;
        }
        for(String lan : languages.keySet())
        {
            String broken = breakForLanguage(encrypted, languages.get(lan));
            int x = countWords(broken, languages.get(lan));
            if(max == x)
                {
                    System.out.println(lan);
                    return broken;
                }
        }
        return null;
    }
    
    public void breakVigenere () {
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            FileResource dicr = new FileResource(f);
            HashSet<String> dictionary = readDictionary(dicr);
            languages.put(f.getName(), dictionary);
        }
        String decrypted = breakForAllLangs(encrypted, languages);
        System.out.println(decrypted);
        
    }
 
}
