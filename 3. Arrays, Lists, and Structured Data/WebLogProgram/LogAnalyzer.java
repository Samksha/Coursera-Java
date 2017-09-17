
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         WebLogParser pr = new WebLogParser();
         FileResource fr = new FileResource(filename);
         for(String s : fr.lines())
         {
             records.add(pr.parseEntry(s));
         }
     }
        
     public int countUniqueIPs()
     {
         int count = 0;
         ArrayList<String> ipArr = new ArrayList<String>();
         for(LogEntry le : records)
         { 
             if(!ipArr.contains(le.getIpAddress()))
             {
                 ipArr.add(le.getIpAddress());
                 count++;
             }
         }
         return count;
     }
     
     public void printAllHigherThanNum(int num)
     {
         for(LogEntry le : records)
         {
             if(le.getStatusCode() > num)
                System.out.println(le);
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday)
     {
         ArrayList<String> arr = new ArrayList<String>();
         for(LogEntry le : records)
         { 
             //System.out.println(le.getAccessTime().toString().substring(4, 10));
             if(le.getAccessTime().toString().substring(4, 10).equals(someday))
                {
                    if(!arr.contains(le.getIpAddress()))
                    arr.add(le.getIpAddress());
                }
         }
         return arr;
     }
     
     public int countUniqueIPsInRange(int low, int high)
     {
         int count = 0;
         ArrayList<String> ipArr = new ArrayList<String>();
         for(LogEntry le : records)
         {
             if(!ipArr.contains(le.getIpAddress()))
             {
                 ipArr.add(le.getIpAddress());                 
                 if(le.getStatusCode() >= low && le.getStatusCode() <= high)
                    count++;
              }   
         }
         return count;
     }
     
     public HashMap<String,Integer> countVisitsPerIP()
     {
         HashMap<String, Integer> HM = new HashMap<String, Integer>();
         for(LogEntry le : records)
         {
             if(!HM.containsKey(le.getIpAddress()))
             {
                 HM.put(le.getIpAddress(), 1);
             }
             else
             {
                 int val = HM.get(le.getIpAddress());
                 HM.put(le.getIpAddress(), val+1);
             }
         }
         return HM;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> HM)
     {
         int max = 0;
         for(Integer i : HM.values())
         {
             if(i > max)
                max = i;
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> HM)
     {
         int max = mostNumberVisitsByIP(HM);
         ArrayList<String> arr = new ArrayList<String>();
         for(String s : HM.keySet())
         {
             if(HM.get(s) == max)
                arr.add(s);
         }
         return arr;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays()
     {
         HashMap<String, ArrayList<String>> HMDays = new HashMap<String, ArrayList<String>>();
         for(LogEntry le : records)
         {
             String s = le.getAccessTime().toString().substring(4, 10);
             if(!HMDays.containsKey(s))
             {
                 ArrayList<String> arr = new ArrayList<String>();
                 arr.add(le.getIpAddress());
                 HMDays.put(s, arr);
             }
             else
             {
                 ArrayList<String> arr = HMDays.get(s);
                 arr.add(le.getIpAddress());
                 HMDays.put(s, arr);
             }
         }
         return HMDays;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> HMDays)
     {
         int max = 0;
         for(String s : HMDays.keySet())
         {
             if(HMDays.get(s).size() > max)
             {
                    max = HMDays.get(s).size();
             }
         }
         for(String s : HMDays.keySet())
         {
             if(HMDays.get(s).size() == max)
             {
                 return s;
             }
         }
         return null;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> HMDays, String someday)
     {
         ArrayList<String> arr = new ArrayList<String>();
         for(String s : HMDays.keySet())
         {
             if(s.equals(someday))
             {
                 arr = HMDays.get(s);
             }
         }
         return arr;
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
          
}
