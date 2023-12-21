/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

import edu.duke.*;

public class LogAnalyzer{
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
         for (LogEntry le : records)
             System.out.println(le);
     }
     public int countUniqueIPs(){
        ArrayList<String> ipList = new ArrayList<String>();
        for(int i = 0; i < records.size(); i++){
            String ipadd = records.get(i).getIpAddress();
            if (!ipList.contains(ipadd))
                ipList.add(ipadd);
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
            String date = records.get(i).getAccessTime().toString();
            if (!ipList.contains(ipadd) && date.contains(someday)){
                // System.out.println("date:" + date);
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
                // System.out.println("Status code: " + statusCode + "unique IP: " + ipadd);
                ipList.add(ipadd);
            }
        }
        return ipList.size();
     }
    public HashMap<String, Integer> countVisitsPerIP(){
        HashMap  <String, Integer> returnMap = new HashMap  <String, Integer>();
        for(LogEntry le : records){
            String ipadd = le.getIpAddress();
            if(returnMap.get(ipadd) == null)
                returnMap.put(ipadd, 1);
            else
                returnMap.put(ipadd, returnMap.get(ipadd) + 1);
        }
        return returnMap;
    }
    public HashMap<String, Integer> countVisitsPerIP(ArrayList <String> iplist){
        HashMap  <String, Integer> returnMap = new HashMap  <String, Integer>();
        for (int index = 0; index < iplist.size(); index++) {
            String ipadd = iplist.get(index);
            if(returnMap.get(ipadd) == null)
                returnMap.put(ipadd, 1);
            else
                returnMap.put(ipadd, returnMap.get(ipadd) + 1);
        }
        return returnMap;
        }
    public int mostNumberVisitsByIP(HashMap  <String, Integer> map){
        int max = 0;
        for (int num : map.values()){
            if (num > max)
                max = num;
        }
        return max;
    }
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map){
        ArrayList<String> IPmaxVisits = new ArrayList<String>();
        int max = mostNumberVisitsByIP(map);
        // System.out.println("Max: " + max);
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if (entry.getValue() == max)
                IPmaxVisits.add(entry.getKey());
        }
        return IPmaxVisits;
    }
    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (LogEntry logEntry : records) {
            String date = logEntry.getAccessTime().toString().substring(0, 10);
            // System.out.println(date);
            if (map.get(date) == null){
                ArrayList <String> al = new ArrayList<String>();
                al.add(logEntry.getIpAddress());
                map.put(date, al);
            }
            else{
                ArrayList <String> al2 = map.get(date);
                al2.add(logEntry.getIpAddress());
                map.put(date, al2);
            }
        }
        return map;
    }
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map){
        int max = 0;
        String date = "";
        for (Map.Entry<String, ArrayList<String>> logEntry : map.entrySet()) {
            if(logEntry.getValue().size() > max){
                max = logEntry.getValue().size();
                 date = logEntry.getKey().substring(4);
            }
        }
        return date;
    }
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String someday){
        HashMap<String, Integer> dayMap = new HashMap<String, Integer>();
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            if (entry.getKey().contains(someday)){
                dayMap = countVisitsPerIP(entry.getValue());
            }
        }
        ArrayList<String> iPsMostVisitOnSomeday = new ArrayList<String>();
        iPsMostVisitOnSomeday = iPsMostVisits(dayMap);
        return iPsMostVisitOnSomeday;
    }
}
