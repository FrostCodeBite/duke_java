package WebServerLogs;

import java.text.*;
import java.util.*;

import edu.duke.FileResource;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);

        for (String line: fr.lines()) {
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.err.println(le);
        }
    }

    public int countUniqueIPs() {

        ArrayList<String> uniqeIP = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddress = le.getIpAddress();
            if (!uniqeIP.contains(ipAddress)) {
                uniqeIP.add(ipAddress);
            }
        }

        return uniqeIP.size();
    }

    public void printAllHigherThanNum(int num) {

        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode > num) {
                System.err.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqeIPonDay = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddress = le.getIpAddress();
            String d = le.getAccessTime().toString();
            SimpleDateFormat form = new SimpleDateFormat("MMM dd");
            String date = form.format(le.getAccessTime());
            if (!uniqeIPonDay.contains(ipAddress)) {
                if (someday.equals(date)) {
                    uniqeIPonDay.add(ipAddress);
                }
            }
        }

        return uniqeIPonDay;
    }

    public int countUniqueIPsInRange(int low, int high) {

        ArrayList<String> uniqeIP = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddress = le.getIpAddress();
            int statusCode = le.getStatusCode();
            if (!uniqeIP.contains(ipAddress)) {
                if (low <= statusCode && statusCode <= high) {
                    uniqeIP.add(ipAddress);
                }
            }
        }

        return uniqeIP.size();
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ipAddress = le.getIpAddress();
            if (!map.containsKey(ipAddress)) {
                map.put(ipAddress, 1);
            } else {
                int freq = map.get(ipAddress);
                map.put(ipAddress, freq+1);
            }
        }
        return map;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> map) {
        
        int mostNum = 0;
        for (String ip : map.keySet()) {
            if (mostNum < map.get(ip)) {
                mostNum = map.get(ip);
            }
        }

        return mostNum;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map) {
        int mostVisitNum = mostNumberVisitsByIP(map);
        ArrayList<String> ipsList = new ArrayList<String>();

        for (String ip : map.keySet()) {
            if (map.get(ip) == mostVisitNum) {
                ipsList.add(ip);
            }
        }

        return ipsList;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();

        for (LogEntry le : records) {
            String ipAddress = le.getIpAddress();
            SimpleDateFormat form = new SimpleDateFormat("MMM dd");
            String date = form.format(le.getAccessTime());

            if (!hm.containsKey(date)) {
                ArrayList<String> ipsList = new ArrayList<>();
                ipsList.add(ipAddress);
                hm.put(date, ipsList);
            } else {
                ArrayList<String> ipsList = hm.get(date);
                ipsList.add(ipAddress);
                hm.put(date, ipsList);
            }
        }

        return hm;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> hm) {
        
        String date = "";
        int ipsNum = 0;
        for(String day : hm.keySet()) {
            if (ipsNum < hm.get(day).size()) {
                ipsNum = hm.get(day).size();
                date = day;
            }
        }

        return date;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> hm, String day) {

        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for (String date : hm.keySet()) {
            ArrayList<String> ipsList = hm.get(date);
            for (int i = 0; i < ipsList.size(); i++) {
                String ip = ipsList.get(i);
                if (date.equals(day)) {
                    if (!map.containsKey(ip)) {
                        map.put(ip, 1);
                    } else {
                        int freq = map.get(ip);
                        map.put(ip, freq+1);
                    }
                }
            }
        }

        ArrayList<String> ipsList = iPsMostVisits(map);

        return ipsList;
    }
}
