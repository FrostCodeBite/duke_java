public class Part1 {
    
    public static int findStopCodon(String dna, int startIndex, String stopCodon) {
            
        //returns the index of the first occurrence of stopCodon that appears past startIndex and is a multiple of 3 away from startIndex.
        int stopIndex = dna.indexOf(stopCodon);
        if (stopIndex == -1) {
            return dna.length();
        }

        if (stopIndex > startIndex) {
            while ((stopIndex - startIndex) % 3 != 0) {
                startIndex = dna.indexOf("ATG",startIndex+1);
                if (startIndex == -1) {
                    stopIndex = dna.indexOf(stopCodon, stopIndex+1);
                    startIndex = 0;
                }
            }

            return stopIndex;
        } else {
            return dna.length();
        }
    }

    public static void testFindStopCodon() {
        String dna = "xTAAxTAAxTAATAGTGA";
        int test1 = findStopCodon(dna, 0, "TAA");
        int test2 = findStopCodon(dna, 0, "TAG");
        int test3 = findStopCodon(dna, 0, "TGA");
        
        System.err.println(test1);
        System.err.println(test2);
        System.err.println(test3);
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

        int tempIndex = 0;
        if (stopIndexTAA < stopIndexTAG) {
            if (stopIndexTAA < stopIndexTGA) {
                tempIndex = stopIndexTAA; 
            } else {
                tempIndex = stopIndexTGA;
            }

        } else {
            if (stopIndexTAG < stopIndexTGA) {
                tempIndex = stopIndexTAG;
            } else {
                tempIndex = stopIndexTGA;
            }
            
        }

        if (tempIndex == dna.length()) {
            return "";
        }


        String subGene = dna.substring(startIndex, tempIndex+3);

        return subGene;
    }

    public static void testFindGene() {
        //DNA with no “ATG”
        String dna1 = "ATGTAAGATGCCCTAGT";
        String test1 = findGene(dna1,0);
        System.err.println(test1);

        //DNA with “ATG” and one valid stop codon
        String dna2 = "ATGxxxTGATAA";
        String test2 = findGene(dna2, 0);
        System.err.println(test2);
        
        //DNA with “ATG” and multiple valid stop codons
        String dna3 = "xATGxxxTAGTGATAA";
        String test3 = findGene(dna3, 0);
        System.err.println(test3);

        //DNA with “ATG” and no valid stop codons.
        String dna4 = "ATGxxx";
        String test4 = findGene(dna4, 0);
        System.err.println(test4);
    }

    public static void printAllGenes(String dna) {
            
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);

            if (currentGene.isEmpty()) {
                
                break;
            }

            System.err.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }

    }

    public static void main(String[] args) {
        // testFindStopCodon();
        testFindGene();
        // String dna = "ATGxxxTAAxxxATGxxxTGAxxxATGxxxTAA";
        // printAllGenes(dna);
    }
}
