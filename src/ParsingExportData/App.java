package ParsingExportData;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class App {
    
    public static void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        // String country = countryInfo(parser, "Nauru");
        // System.err.println(country);
        // listExportersTwoProducts(parser,"cotton","flowers");
        // int numExporters = numberOfExporters(parser, "cocoa");
        // System.err.println(numExporters);
        bigExporters(parser, "$999,999,999,999");
    }

    public static String countryInfo(CSVParser parser, String country) {
        
        for (CSVRecord record: parser) {
            String countryRecord = record.get("Country");
            if (countryRecord.equals(country)) {
                String exportRecord = record.get("Exports");
                String valueRecord = record.get("Value (dollars)");
                String data = countryRecord+": "+exportRecord+": "+valueRecord;
                return data;
            }
        }
        return "NOT FOUND";
    }

    public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record: parser) {
            String exportRecord = record.get("Exports");
            if (exportRecord.contains(exportItem1) && exportRecord.contains(exportItem2)) {
                String countryRecord = record.get("Country");
                System.err.println(countryRecord);
            }
        }
        
    }

    public static int numberOfExporters(CSVParser parser, String exportItem) {
        
        int num = 0;
        
        for (CSVRecord record: parser) {
            String exportRecord = record.get("Exports");
            if (exportRecord.contains(exportItem)) {
                num++;
            }
        }

        return num;
    }

    public static void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record: parser) {
            String amountRecord = record.get("Value (dollars)");
            if (amountRecord.length() > amount.length()) {
                String countryRecord = record.get("Country");
                String exportRecord = record.get("Exports");
                String data = countryRecord+": "+exportRecord+": "+amountRecord;
                System.err.println(data);
            }
        }
    }

    public static void main(String[] args) {
        
        tester();
    }
}
