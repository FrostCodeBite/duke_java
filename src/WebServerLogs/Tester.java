package WebServerLogs;

import java.util.ArrayList;
import java.util.HashMap;

public class Tester {
    public static void testLogAnalyzer() {
        LogAnalyzer obj = new LogAnalyzer();
        obj.readFile("./logs/weblog2_log.txt");
        obj.printAll();
    }

    public static void testUniqueIP() {
        LogAnalyzer obj = new LogAnalyzer();
        obj.readFile("./logs/weblog2_log.txt");
        int unique = obj.countUniqueIPs();
        System.err.println("Unique IPs are: "+unique);

        int statusNum = 400;
        obj.printAllHigherThanNum(statusNum);

        String day = "Sep 24";
        ArrayList<String> uniqueIPsList = obj.uniqueIPVisitsOnDay(day);
        System.err.println("Unique IPs on date "+day+" are: "+uniqueIPsList.size());

        int low = 200;
        int high = 299;
        int uniqueRange = obj.countUniqueIPsInRange(low, high);
        System.err.println("Unique IPs in Range "+low+" - "+high+" are: "+uniqueRange);
    }

    public static void tester() {
        LogAnalyzer obj = new LogAnalyzer();
        obj.readFile("./logs/weblog2_log.txt");
        HashMap<String, Integer> map = obj.countVisitsPerIP();
        for (String ip : map.keySet()) {
            System.err.println(ip+" : "+map.get(ip));
        }

        int mostNumVisit = obj.mostNumberVisitsByIP(map);
        System.err.println("Maximum number of visits: "+mostNumVisit);

        ArrayList<String> ipList = obj.iPsMostVisits(map);
        for (int i = 0; i < ipList.size(); i++) {
            System.err.println(ipList.get(i));
        }

        HashMap<String, ArrayList<String>> hm = obj.iPsForDays();
        for (String date : hm.keySet()) {
            System.err.println(date + " : "+hm.get(date));
        }

        String dayWithMostIPs = obj.dayWithMostIPVisits(hm);
        System.err.println("Day with most IPs is: "+dayWithMostIPs);

        ArrayList<String> ipList2 = obj.iPsWithMostVisitsOnDay(hm, "Sep 29");
        for (int i = 0; i < ipList2.size(); i++) {
            System.err.println(ipList2.get(i));
        }
    }

    public static void main(String[] args) {
        // testLogAnalyzer();
        // testUniqueIP();
        tester();
    }
}
