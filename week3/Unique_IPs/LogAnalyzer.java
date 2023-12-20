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
         records = new ArrayList <LogEntry>();
     }
        
     public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for(String line : fr.lines()){
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     public int countUniqueIPs(){
        ArrayList<String> ipList = new ArrayList<String>();
            for(int i = 0; i < records.size(); i++){
                String ipadd = records.get(i).getIpAddress();
                if (!ipList.contains(ipadd)){
                    ipList.add(ipadd);
                }
            }
            return ipList.size();
     }
     public void printAllHigherThanNum(int num){
        for(int i = 0; i < records.size(); i++){
                int statusCode = records.get(i).getStatusCode();
                if (statusCode > num)
                    System.out.println(records.get(i));
            }
     }
     public ArrayList<String> uniqueIpVisitsOnDay(String someday){
        ArrayList<String> ipList = new ArrayList<String>();
            for(int i = 0; i < records.size(); i++){
                String ipadd = records.get(i).getIpAddress();
                if (!ipList.contains(ipadd) && records.get(i).getAccessTime().toString().contains(someday)){
                    System.out.println("date: " + records.get(i).getAccessTime().toString());
                    ipList.add(ipadd);
                }
            }
            return ipList;
     }
     public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> ipList = new ArrayList<String>();
            for(int i = 0; i < records.size(); i++){
                int statusCode = records.get(i).getStatusCode();
                String ipadd = records.get(i).getIpAddress();
                if (!ipList.contains(ipadd) && statusCode >= low && statusCode <= high){
                    System.out.println("Status code: " + statusCode + "unique IP: " + ipadd);
                    ipList.add(ipadd);
                }
            }
            return ipList.size();
     }
}
