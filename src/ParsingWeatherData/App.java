package ParsingWeatherData;

import java.io.File;

import org.apache.commons.csv.*;

import edu.duke.*;

public class App {

    public static CSVRecord coldestHourInFile(CSVParser parser) {

        CSVRecord coldestRecord = null; 

        for (CSVRecord record : parser) {
            if (coldestRecord == null) {
                coldestRecord = record;
            } else {
                double currentTemp = Double.parseDouble(record.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));

                if ((coldestTemp > currentTemp) && (currentTemp != -9999)) {
                    coldestRecord = record;
                }
            }
        }
        
        return coldestRecord;
    }

    public static void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRecord = coldestHourInFile(parser);
        String date = coldestRecord.get("DateUTC");
        String temp = coldestRecord.get("TemperatureF");
        System.err.println(date+", "+temp);
    }

    //Part2

    public static File fileWithColdestTemperature() {

        File coldestFile = null;
        CSVRecord coldestRecord = null; 

        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord record = coldestHourInFile(parser);

            if (coldestRecord == null) {
                coldestRecord = record;
            } else {
                double currentTemp = Double.parseDouble(record.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));

                if ((coldestTemp > currentTemp) && (currentTemp != -9999)) {
                    coldestRecord = record;
                    coldestFile = f;
                }
            }
        }

        return coldestFile;
    }

    public static void testFileWithColdestTemperature() {
        File coldestFile = fileWithColdestTemperature();
        FileResource fr = new FileResource(coldestFile);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRecord = coldestHourInFile(parser);
        String coldestTemp = coldestRecord.get("TemperatureF");
        
        System.err.println("Coldest day was in file "+coldestFile.getName());
        System.err.println("Coldest temperature on that day was "+coldestTemp);
        System.err.println("All the Temperatures on the coldest day were:");
        CSVParser parser2 = fr.getCSVParser(); 
        for (CSVRecord record: parser2) {
            String date = record.get("DateUTC");
            String temp = record.get("TemperatureF");
            System.err.println(date+", "+temp);
        }
    }

    //Part3

    public static CSVRecord lowestHumidityInFile(CSVParser parser) {
        
        CSVRecord lowestRecord = null; 

        for (CSVRecord record : parser) {
            if (lowestRecord == null) {
                lowestRecord = record;
            } else {

                if (!record.get("Humidity").equals("N/A")) {
                    double currentHumidity = Double.parseDouble(record.get("Humidity"));
                    double lowestHumidity = Double.parseDouble(lowestRecord.get("Humidity"));

                    if (lowestHumidity > currentHumidity) {
                        lowestRecord = record;
                    }
                }
            }
        }

        return lowestRecord;
    }

    public static void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);

        String date = csv.get("DateUTC");
        String humidity = csv.get("Humidity");
        System.err.println("Lowest Humidity was "+humidity+" at "+date);
    }

    public static CSVRecord lowestHumidityInManyFiles() {
        
        CSVRecord lowestRecord = null; 

        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord record = lowestHumidityInFile(parser);

            if (lowestRecord == null) {
                lowestRecord = record;
            } else {
                if (!record.get("Humidity").equals("N/A")) {
                    double currentHumidity = Double.parseDouble(record.get("Humidity"));
                    double lowestHumidity = Double.parseDouble(lowestRecord.get("Humidity"));

                    if (lowestHumidity > currentHumidity) {
                        lowestRecord = record;
                    }
                }
            }
        }
        return lowestRecord;
    }

    public static void testLowestHumidityInManyFiles() {
        CSVRecord csv = lowestHumidityInManyFiles();

        String date = csv.get("DateUTC");
        String humidity = csv.get("Humidity");
        System.err.println("Lowest Humidity was "+humidity+" at "+date);
    }

    //Part4

    public static double averageTemperatureInFile(CSVParser parser) {

        double avgTemp = 0.0; 
        double sumTemp = 0.0;
        int count = 0;

        for (CSVRecord record : parser) {
            double currentTemp = Double.parseDouble(record.get("TemperatureF"));
            sumTemp = sumTemp + currentTemp;
            count++;
        }
        
        avgTemp = sumTemp/count;

        return avgTemp;
    }

    public static void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureInFile(parser);
        System.err.println("Average temperature in file is "+avgTemp);
    }

    public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
       
        double avgTemp = 0.0; 
        double sumTemp = 0.0;
        int count = 0;

        for (CSVRecord record : parser) {
            double humidity = Double.parseDouble(record.get("Humidity"));
            if (humidity >= value) {
                double currentTemp = Double.parseDouble(record.get("TemperatureF"));
                sumTemp = sumTemp + currentTemp;
                count++;
            }
        }
        
        avgTemp = sumTemp/count;

        return avgTemp;
    }

    public static void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (avgTemp == 0.0) {
            System.err.println("No temperatures with that humidity");
        } else {
            System.err.println("Average Temp when high Humidity is "+avgTemp);
        }
    }

    public static void main(String[] args) {
        // testColdestHourInFile();
        testFileWithColdestTemperature();
        // testLowestHumidityInFile();
        // testLowestHumidityInManyFiles();
        // testAverageTemperatureInFile();
        // testAverageTemperatureWithHighHumidityInFile();
    }

}
