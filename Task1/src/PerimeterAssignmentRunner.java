import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }

    public void testPerimeter () {
        // FileResource fr = new FileResource();
        // Shape s = new Shape(fr);
        // double length = getPerimeter(s);
        // int points = getNumPoints(s);
        // double avgLength = getAverageLength(s);
        // double largetSide = getLargestSide(s);
        // double largetSideX = getLargestX(s);
        // double largestPerim = getLargestPerimeterMultipleFiles();
        String largestPerimFileName = getFileWithLargestPerimeter();
        // System.out.println("perimeter = " + length);
        // System.out.println("number of points = " + points);
        // System.out.println("average length = " + avgLength);
        // System.out.println("largest side = " + largetSide);
        // System.out.println("largest side X = " + largetSideX);
        // System.out.println("largest perim = " + largestPerim);
        System.out.println("largest perim file name = " + largestPerimFileName);
    }

    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int numPoints = 0;

        for (Point cuuPoint : s.getPoints()) {
            numPoints += 1;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double length = getPerimeter(s);
        int points = getNumPoints(s);

        double avgLength = length/points;
        
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        Point prevPt = s.getLastPoint();
        double largestSide = 0; 
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > largestSide) {
                largestSide = currDist;
            }
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        Point prevPt = s.getLastPoint();
        int prevPtX = prevPt.getX();
        double largestX = prevPtX; 
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            int newX = currPt.getX();
            if (newX > largestX) {
                largestX = newX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if(length > largestPerim) {
                largestPerim = length;
            }
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if(length > largestPerim) {
                largestPerim = length;
                temp = f;
            }
        }
        return temp.getName();
    }
}
