
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "earthquake_data/earthQuakeDataDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        // sortByMagnitude(list);
        // sortByLargestDepth(list);
        // sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        // sortByMagnitudeWithCheck(list);
        // sortByLargestDepthWithCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "earthquake_data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int largestIndex = from;

        for (int i = from+1; i < quakeData.size(); i++) {
            double currDepth = quakeData.get(i).getDepth();
            double largestDepth = quakeData.get(largestIndex).getDepth();

            if (currDepth > largestDepth) {
                largestIndex = i;
            }
        }

        return largestIndex;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {

        for (int i = 0; i < in.size(); i++) {
            int largestIndex = getLargestDepth(in, i);

            QuakeEntry currQE = in.get(i);
            QuakeEntry largestQE = in.get(largestIndex);

            in.set(i, largestQE);
            in.set(largestIndex, currQE);
        } 
    }

    public static void main(String[] args) {
        QuakeSortInPlace obj = new QuakeSortInPlace();
        obj.testSort();
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        int pass = quakeData.size()-numSorted-1;

        for (int i = 0; i < pass; i++) {
            QuakeEntry currQE = quakeData.get(i);
            QuakeEntry nextQE = quakeData.get(i+1);

            /* SORT MANITUDE FROM SMALLEST TO LARGESET */
            if (currQE.getMagnitude() > nextQE.getMagnitude()) {
                quakeData.set(i, nextQE);
                quakeData.set(i+1, currQE);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {

        for (int i = 0; i < in.size(); i++) {
            onePassBubbleSort(in, i);
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        boolean isSorted = true;

        for (int i = 0; i < quakes.size()-1; i++) {
            double currMag = quakes.get(i).getMagnitude();
            double nextMag = quakes.get(i+1).getMagnitude();

            /* CHECK IF MAGNITUDE SMALLER THAN NEXT ONE */
            if (currMag > nextMag) {
                isSorted = false;
            }
        }

        return isSorted;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int passes = 0;
        for (int i = 0; i < in.size(); i++) {
            onePassBubbleSort(in, i);

            passes++;
            if (checkInSortedOrder(in)) {
                break;
            }
        }
        System.err.println("Passes are: "+passes);
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int passes = 0;
        for (int i = 0; i < in.size(); i++) {
            
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);

            passes++;
            if (checkInSortedOrder(in)) {
                break;
            }
        }
        System.err.println("Passes are: "+passes);
    }

    public void sortByLargestDepthWithCheck(ArrayList<QuakeEntry> in) {
        int passes = 0;
        for (int i = 0; i < in.size(); i++) {
            int largestIndex = getLargestDepth(in, i);

            QuakeEntry currQE = in.get(i);
            QuakeEntry largestQE = in.get(largestIndex);

            in.set(i, largestQE);
            in.set(largestIndex, currQE);

            passes++;

            if (passes == 50) {
                break;
            }
            if (checkInSortedOrderDepth(in)) {
                break;
            }
        }
        System.err.println("Passes are: "+passes);
    }

    public boolean checkInSortedOrderDepth(ArrayList<QuakeEntry> quakes) {
        boolean isSorted = true;

        for (int i = 0; i < quakes.size()-1; i++) {
            double currDepth = quakes.get(i).getDepth();
            double nextDepth = quakes.get(i+1).getDepth();

            /* CHECK IF DEPTH LARGER THAN NEXT ONE */
            if (currDepth < nextDepth) {
                isSorted = false;
            }
        }

        return isSorted;
    }
}
