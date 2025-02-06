public class Part2 {

    public static String findSimpleGene(String dna, String start, String stop) {

            
        int startCondon = dna.toUpperCase().indexOf(start.toUpperCase());
        if (startCondon == -1) {
            return "No ATG";
        }

        int endCondon = dna.toUpperCase().indexOf(stop.toUpperCase());
        if (endCondon ==  -1) {
            return "No TAA";
        }

        String Gene = dna.toUpperCase().substring(startCondon, endCondon+3);
        if (Gene.length() % 3 != 0) {
            return "Gene Length is not mod of 3";
        }
        
        return Gene;
    }

    public static void testSimpleGene() {
        // DNA with no "ATG"
        String DNA = "CTGTAA";
        String result = findSimpleGene(DNA, "ATG", "TAA");
        System.err.println(result);

        // DNA with no “TAA”
        String DNA2 = "ATGCTG";
        String result2 = findSimpleGene(DNA2, "ATG", "TAA");
        System.err.println(result2);

        // DNA with no “ATG” or “TAA”;


        // DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene)
        String DNA4 = "atgatgtaa";
        String result4 = findSimpleGene(DNA4, "ATG", "TAA");
        System.err.println(result4);

        // DNA with ATG, TAA and the substring between them is not a multiple of 3
        String DNA5 = "ATGTGTAA";
        String result5 = findSimpleGene(DNA5, "ATG", "TAA");
        System.err.println(result5);
    }

    public static void main(String[] args) {
        testSimpleGene();
    }
}
