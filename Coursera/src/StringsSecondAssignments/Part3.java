package StringsSecondAssignments;

public class Part3 {

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

    public static int countGenes(String dna) {
        
        int startIndex = 0;
        int num = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);

            if (currentGene.isEmpty()) {
                
                break;
            }

            num++;
            System.err.println(num);
            System.err.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }

        return num;
    }

    public static void main(String[] args) {
        // String dna = "ATGTAAGATGCCCTAGT";
        // countGenes(dna);

        String dna2 = "CTGCCTGCATGATCGTA";
        countGenes(dna2);
    }
}
