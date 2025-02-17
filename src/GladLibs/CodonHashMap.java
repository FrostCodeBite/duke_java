package GladLibs;

import java.util.HashMap;

public class CodonHashMap {
    private static HashMap<String, Integer> map;

    public CodonHashMap() {
        map = new HashMap<String, Integer>();
    }

    public HashMap<String, Integer> getCodonHashMap(){
        return map;
    }

    public void buildCodonMap(int start, String dna) {

        for (int i = start; i < dna.length(); i+=3) {
            if (i <= dna.length()-3) {
                String codon = dna.substring(i, i+3);
                if (!map.containsKey(codon)) {
                    map.put(codon, 1);
                } else {
                    map.put(codon, map.get(codon)+1);
                }
            }
        }
    }

    public String getMostCommonCodon() {
        
        String mostCommon = "";
        int mostFreq = 0;

        for (String c : map.keySet()) {
            int freq = map.get(c);

            if (mostFreq < freq) {
                mostCommon = c;
                mostFreq = freq;
            }
        } 
        return mostCommon;
    }

    public void printCodonCounts(int start, int end) {
        for (String c : map.keySet()) {
            if (start <= map.get(c) && map.get(c) <= end) {
                System.err.println(c + " : "+map.get(c));
            } 
        } 
    }

    public static void tester() {
        CodonHashMap map = new CodonHashMap();
        String dna = "CAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCTAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCCAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATC";
        int start = 0;
        map.buildCodonMap(start, dna);
        HashMap<String, Integer> data = map.getCodonHashMap();
        int uniqueCodon = data.size();
        System.err.println("Reading frame starting with "+start+" results in "+uniqueCodon+" unique codons");

        String commonCodon = map.getMostCommonCodon();
        System.err.println("and most common codon is "+ commonCodon + " with count "+data.get(commonCodon));

        int startIndex = 7;
        int endIndex = 7;
        System.err.println("Counts of codons between "+startIndex+ " and "+endIndex+ " inclusive are:");
        map.printCodonCounts(startIndex, endIndex);

    }

    public static void main(String[] args) {
        tester();
    }

    public static HashMap<String, Integer> getMap() {
        return map;
    }

}
