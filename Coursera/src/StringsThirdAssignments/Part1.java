package StringsThirdAssignments;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class Part1 {
    
    public static int findStopCodon(String dna, int startIndex, String stopCodon) {
            
        //returns the index of the first occurrence of stopCodon that appears past startIndex and is a multiple of 3 away from startIndex.
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if (stopIndex == -1) {
            return dna.length();
        }

        while (stopIndex != -1) {
            if ((stopIndex - startIndex) % 3 == 0) {
                return stopIndex;
            } else {
                stopIndex = dna.indexOf(stopCodon, stopIndex+1);
            }
        }

        return dna.length();
    }

    public static String findGene(String dna, int where) {
        //Find the index of the first occurrence of the start codon “ATG”. If there is no “ATG”, return the empty string.

        int startIndex = dna.indexOf("ATG", where); 
        if (startIndex == -1) {
            return "";
        }

        //Find the index of the first occurrence of the stop codon “TAA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. Hint: call findStopCodon.
        int stopIndexTAA = findStopCodon(dna, startIndex, "TAA");

        //Find the index of the first occurrence of the stop codon “TAG” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. 
        int stopIndexTAG = findStopCodon(dna, startIndex, "TAG");
        
        //Find the index of the first occurrence of the stop codon “TGA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. 
        int stopIndexTGA = findStopCodon(dna, startIndex, "TGA");

        //Return the gene formed from the “ATG” and the closest stop codon that is a multiple of three away. If there is no valid stop codon and therefore no gene, return the empty string.

        int tempIndex = Math.min(Math.min(stopIndexTAA, stopIndexTAG), stopIndexTGA);
        if (tempIndex == dna.length()) {
            return "";
        }

        String subGene = dna.substring(startIndex, tempIndex+3);

        return subGene;
    }

    public static StorageResource getAllGenes(String dna) {

        StorageResource geneList = new StorageResource();
            
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);

            if (currentGene.isEmpty()) {
                
                break;
            }

            // System.err.println(currentGene);
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex+1) + currentGene.length();
        }

        return geneList;
    }

    // Part 2

    public static int howMany(String stringa, String stringb) {

        String tempString = stringb.replaceAll(stringa, "");
        int count = (stringb.length() - tempString.length())/stringa.length();
        
        return count;
    }

    public static float cgRatio(String dna) {
        int numC = howMany("C", dna);
        int numG = howMany("G", dna);

        float ratio = ((float)numC + (float)numG)/dna.length();

        return ratio;
    }

    public static int countCTG(String dna) {
        int numCTG = howMany("CTG", dna);

        return numCTG;
    }

    //Part 3

    public static void processGenes(StorageResource sr) {

        String longestGene = "";

        int countTotal = 0;
        int count = 0;
        int countCG = 0;

        for (String gene : sr.data()) {
            if (gene.length() > 60) {
                System.err.println("Strings in sr that are longer than 9 characters: "+ gene);
                count++;
            }

            if (cgRatio(gene) > 0.35) {
                System.err.println("Strings in sr whose C-G-ratio is higher than 0.35: "+ gene);
                
                countCG++;
            }

            if (gene.length() > longestGene.length()) {
                longestGene = gene;
            }

            countTotal++;
        }
        System.err.println("Total number of Strings in sr: "+ countTotal);
        System.err.println("Number of Strings in sr that are longer than 9 characters: "+ count);
        System.err.println("Number of strings in sr whose C-G-ratio is higher than 0.35: "+ countCG);
        System.out.println("Longest Gene is: " + longestGene);
        System.out.println("Length of Longest Gene is: " + longestGene.length());
    }

    public static void testProcessGenes() {

        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        processGenes(getAllGenes(dna.toUpperCase()));
        int countCTGinLongest = countCTG(dna);
        System.out.println("Number of CTG in Longest Gene are: " + countCTGinLongest);
    }

    public static void main(String[] args) {
        testProcessGenes();
    }
    
}
