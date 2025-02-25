import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for (QuakeEntry qe : quakeData) {
            double currMag = qe.getMagnitude();
            if (currMag > magMin) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            Location currLoc = qe.getLocation();
            double currDist = currLoc.distanceTo(from);
            if (currDist/1000 < distMax) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "earthquake_data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        /* FILTER AND PRINT MAGNITUDE */
        ArrayList<QuakeEntry> answer = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
        System.err.println("Found "+answer.size()+" quakes that match that criteria");

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "earthquake_data/nov20quakedatasmall.atom";
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        // Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> answer = filterByDistanceFrom(list, 1000, city);
        for (QuakeEntry qe : answer) {
            System.out.printf("%4.2f, %s\n",
                qe.getLocation().distanceTo(city)/1000,
                qe.getInfo());
        }
        System.err.println("Found "+answer.size()+" quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "earthquake_data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for (QuakeEntry qe : quakeData) {
            double currDept = qe.getDepth();
            if (minDepth < currDept && currDept < maxDepth) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "earthquake_data/nov20quakedata.atom";
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        double minDepth = -4000.0;
        double maxDepth = -2000.0;
        System.err.println("Find quakes with depth between "+minDepth+" and "+maxDepth);
        ArrayList<QuakeEntry> answer = filterByDepth(list, minDepth, maxDepth);
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
        System.err.println("Found "+answer.size()+" quakes that match that criteria");
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for (QuakeEntry qe : quakeData) {
            String currPhrase = "";
            String currAnyPhase = qe.getInfo();
            String currStartPhase = qe.getInfo().substring(0, qe.getInfo().lastIndexOf(" "));
            String currEndPhase = qe.getInfo().substring(qe.getInfo().lastIndexOf(" ")+1);

            if (where.equals("start")) {
                currPhrase = currStartPhase;
            } else if (where.equals("end")) {
                currPhrase = currEndPhase;
            } else {
                currPhrase = currAnyPhase;
            }

            if (currPhrase.contains(phrase)) {
                answer.add(qe);
            }           
        }

        return answer;
    }

    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "earthquake_data/nov20quakedata.atom";
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        String where = "end";
        String phrase = "Can";
        ArrayList<QuakeEntry> answer = filterByPhrase(list, where, phrase);
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
        System.err.println("Found "+answer.size()+" quakes that match "+phrase+" at "+where);
    }
    
    public static void main(String[] args) {
        EarthQuakeClient obj = new EarthQuakeClient();
        // obj.bigQuakes();
        // obj.closeToMe();
        // obj.quakesOfDepth();
        obj.quakesByPhrase();

        ClosestQuakes obj2 = new ClosestQuakes();
        // obj2.findClosestQuakes();

        LargestQuakes obj3 = new LargestQuakes();
        // obj3.findLargestQuakes();
    }
}
