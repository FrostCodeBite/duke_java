package BabyNames;

import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class App {

    public static void totalBirth(FileResource fr) {
        
        int totalBirths = 0;
        int girlNames = 0;
        int boyNames = 0;
        int totalNames = 0;

        for (CSVRecord record: fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;
            totalNames++;
            
            String sexRecord = record.get(1);
            
             if (sexRecord.equals("F")) {
                girlNames++;
             } else {
                boyNames++;
             }
        }

        System.err.println("Total Births: "+totalBirths);
        System.err.println("Number of girls names: "+girlNames);
        System.err.println("Number of boys names: "+boyNames);
        System.err.println("Total Names: "+totalNames);
    }

    public static void testTotalBirth() {
        FileResource fr = new FileResource();
        totalBirth(fr);
    }

    //Part2

    public static int getRank(int year, String name, String gender) {
        
        // FileResource fr = new FileResource("us_babynames/us_babynames_test/yob"+year+"short.csv");
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        int birth = 0; 
        int currentRank = 0;

        for (CSVRecord record: fr.getCSVParser(false)){
            
            String nameRecord = record.get(0);
            String sexRecord = record.get(1);
            int birthRecord = Integer.parseInt(record.get(2));

            if (birth < birthRecord) {
                if (sexRecord.equals(gender)) {
                    currentRank++;
                    if (nameRecord.equals(name)) {
                        return currentRank;
                    }
                }
            }    
        }

        return -1;
    }

    public static int getRank(FileResource fr, int year, String name, String gender) {
        
        // FileResource fr = new FileResource("us_babynames/us_babynames_test/yob"+year+"short.csv");
        // FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        int birth = 0; 
        int currentRank = 0;

        for (CSVRecord record: fr.getCSVParser(false)){
            
            String nameRecord = record.get(0);
            String sexRecord = record.get(1);
            int birthRecord = Integer.parseInt(record.get(2));

            if (birth < birthRecord) {
                if (sexRecord.equals(gender)) {
                    currentRank++;
                    if (nameRecord.equals(name)) {
                        return currentRank;
                    }
                }
            }    
        }

        return -1;
    }

    public static void testGetRank() {
        int rank = getRank(1971, "Frank", "M");
        System.err.println(rank);
    }

    //Part3

    public static String getName(int year, int rank, String gender) {
        // FileResource fr = new FileResource("us_babynames/us_babynames_test/yob"+year+"short.csv");
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");

        int birth = 0;
        int currentRank = 0;

        for (CSVRecord record: fr.getCSVParser(false)){
            
            String nameRecord = record.get(0);
            String sexRecord = record.get(1);
            int birthRecord = Integer.parseInt(record.get(2));

            if (sexRecord.equals(gender)) {
                if (birth < birthRecord) {
                    currentRank++;
                }

                if (currentRank == rank) {
                    return nameRecord;
                }
            }       
        }

        return "NO NAME";
    }

    public static void testGetName() {
        String nameString = getName(1982, 450, "M");
        System.err.println(nameString);
    }

    //Part4

    public static String whatIsNameInYear(String name, int year, int newYear, String gender) {
        
        //determine the rank of name in the year they were born
        int rankYearBorn = getRank(year, name, gender);

        //print the name born in newYear that is at the same rank and same gender
        String nameString = getName(newYear, rankYearBorn, gender);
        return nameString;
    }

    public static void testWhatIsNameInYear() {
        String nameString = "Owen";
        int year = 1974;
        int newYear = 2014; 
        String genderString = "M";
        String newNameString = whatIsNameInYear(nameString, year, newYear, genderString);
        System.err.println(nameString+" born in "+year+" would be "+newNameString+" if she was born in "+newYear);
    }

    //Part5

    public static int yearOfHighestRank(String name, String gender) {

        DirectoryResource dr = new DirectoryResource();

        int currentRank = 0;
        int currentYear = 0;
        
        for (File f: dr.selectedFiles()) {
            String fname = f.getName();
            int yearTemp = Integer.parseInt(fname.substring(3,7));
            FileResource fr = new FileResource(f);
            int rankRecord = getRank(fr, yearTemp, name, gender);

            if (rankRecord != -1) {
                if (currentRank == 0) {
                    currentYear = yearTemp;
                    currentRank = rankRecord;
                }

                if (currentRank > rankRecord) {
                    currentYear = yearTemp;
                    currentRank = rankRecord;
                }
            }
        }

        return currentYear;
    }

    public static void testYearOfHighestRank() {
        int year = yearOfHighestRank("Genevieve", "M");
        System.err.println(year);
    }

    //Part6

    public static double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();

        double avgRank = 0.0;
        double sumRank = 0.0;
        int tempRank = 0; 
        int count = 0;
        
        for (File f: dr.selectedFiles()) {
            String fname = f.getName();
            int yearTemp = Integer.parseInt(fname.substring(3,7));
            FileResource fr = new FileResource(f);

            tempRank = getRank(fr,yearTemp,name, gender); 

            if (tempRank != -1 ) {
                sumRank = sumRank + (double) tempRank;
                count++;
            }
        }

        avgRank = sumRank / count;
        return avgRank;
    }

    public static void testGetAverageRank() {
        double avgRank = getAverageRank("Robert", "F");
        System.err.println(avgRank);
    }

    //Part7

    public static int getTotalBirthsRankedHigher(int year, String name, String gender) {
        
        // FileResource fr = new FileResource("us_babynames/us_babynames_test/yob"+year+"short.csv");
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");

        int totalBirth = 0;
        int birth = 0;

        
        for (CSVRecord record: fr.getCSVParser(false)){
            
            String nameRecord = record.get(0);
            String sexRecord = record.get(1);
            int birthRecord = Integer.parseInt(record.get(2));

            if (birth < birthRecord) {
                if (sexRecord.equals(gender)) {
                    if (nameRecord.equals(name)) {
                        return totalBirth;
                    }
                    totalBirth += birthRecord;
                }
            }    
        }

        return 0;
    }

    public static void testGetTotalBirthsRankedHigher() {
        int totalBirth = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.err.println(totalBirth);
    }

    public static void main(String[] args) {
        // testTotalBirth();
        // testGetRank();
        // testGetName();
        // testWhatIsNameInYear();
        // testYearOfHighestRank();
        // testGetAverageRank();
        testGetTotalBirthsRankedHigher();
    }
}
