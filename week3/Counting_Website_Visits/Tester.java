
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
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        System.out.println("Unique IPs: " + la.countUniqueIPs());
    }
    public void testPrintAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    public void testUniqueIpVisitsOnDay(){
        ArrayList <String> al = new ArrayList<>();
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        String someDay = "Mar 24";
        al = la.uniqueIpVisitsOnDay(someDay);
        System.out.println("Unique ip on day: " + al);
        System.out.println("Size: " + al.size());
    }
        public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int count = la.countUniqueIPsInRange(300, 399);
        System.out.println("Count Unique ip in range: " + count);
    }
    public void testMostNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap  <String, Integer> result = la.countVisitsPerIP();
        System.out.println("most num visits: " + la.mostNumberVisitsByIP(result));
    }
    public void testIPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap  <String, Integer> result = la.countVisitsPerIP();
        System.out.println("ip list: " + la.iPsMostVisits(result));
    }
    public void testIpWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        // HashMap<String, ArrayList<String>> intermed = la.iPsForDays();
        // System.out.println("Intermed: " + intermed);
        ArrayList<String> result = la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Mar 17");
        System.out.println("Result: " +  result);
    }
    public static void main(String[] args) {
        Tester test =  new Tester();
        // test.testLogEntry();
        // test.testLogAnalyzer();
        // test.testUniqueIP();
        // test.testPrintAllHigherThanNum();
        // test.testUniqueIpVisitsOnDay();
        // test.testCountUniqueIPsInRange();
        // test.testMostNumberVisitsByIP();
        // test.testIPsMostVisits();
        test.testIpWithMostVisitsOnDay();
        // LogAnalyzer la = new LogAnalyzer();
        // la.readFile("weblog1_log");
        // HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        // map = la.iPsForDays();
        // System.out.println("day:" + la.dayWithMostIPVisits(map));
    }
}
