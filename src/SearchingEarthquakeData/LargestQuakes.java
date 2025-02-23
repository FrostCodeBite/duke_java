import java.util.*;

public class LargestQuakes {
    public LargestQuakes() {
        
    }

    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "earthquake_data/nov20quakedata.atom";
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        // int maxIndex = indexOfLargest(list);
        // System.err.println(maxIndex+" : "+list.get(maxIndex));

        ArrayList<QuakeEntry> large = getLargest(list, 50);
        for(int k=0; k < large.size(); k++){
            System.err.println(large.get(k));
        }
        System.out.println("number found: "+large.size());
    }

    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int index = 0;

        for (int i = 0; i < data.size(); i++) {
            double currMag = data.get(i).getMagnitude();

            if (currMag > data.get(index).getMagnitude()) {
                index = i;
            }
        } 

        return index;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // TO DO
        for (int j = 0; j < howMany; j++) {
            int maxIndex = indexOfLargest(copy);;

            ret.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }
        
        return ret;
    }
}
