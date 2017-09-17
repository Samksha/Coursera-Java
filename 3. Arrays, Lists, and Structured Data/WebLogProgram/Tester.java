
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAll();
    }
    
    public void testCountUniqueIPs()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log"); 
        int x = la.countUniqueIPs();
        System.out.println("Unique IPs = " + x);
    }
    
    public void testcountUniqueIPsInRange()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log"); 
        int x = la.countUniqueIPsInRange(200, 299);
        System.out.println(x);
    }
    
    public void testPrintAllHigherThanNum()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log"); 
        la.printAllHigherThanNum(1);
    }
    
    public void testOnDayIP()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log"); 
        ArrayList<String> arr = la.uniqueIPVisitsOnDay("Sep 24");
        for(String s : arr)
        {
            System.out.println(s);
        }
        System.out.println("Size = " + arr.size());
    }
    
    public void testCountVisitsPerIp()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log.txt");
        HashMap<String, Integer> HM = la.countVisitsPerIP();
        for(String s: HM.keySet())
        {
            System.out.println(s);
        }
        System.out.println("Max visits = " + la.mostNumberVisitsByIP(HM));
        ArrayList<String> arr = la.iPsMostVisits(HM);
        for(String s : arr)
        {
            System.out.println(s);
        }
    }
    
    public void testiPsForDays()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log.txt"); 
        HashMap<String, ArrayList<String>> HMDays = la.iPsForDays();
        /*for(String s : HMDays.keySet())
        {
            System.out.println("Key = " + s);
            ArrayList<String> arr = HMDays.get(s);
            for(String t : arr)
                System.out.println(t);
        }*/
        System.out.println(la.dayWithMostIPVisits(HMDays));
        ArrayList<String> arr = la.iPsWithMostVisitsOnDay(HMDays, "Sep 30");
        for(String s : arr)
        {
            System.out.println(s);
        }
    }
}
