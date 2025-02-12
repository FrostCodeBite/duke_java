package StringsFirstAssignments;

public class Part1 {

    public static String findSimpleGene(String condonString) {
            
        int startCondon = condonString.indexOf("ATG");
        if (startCondon == -1) {
            return "No ATG";
        }

        int endCondon = condonString.indexOf("TAA");
        if (endCondon ==  -1) {
            return "No TAA";
        }

        String Gene = condonString.substring(startCondon, endCondon+3);
        if (Gene.length() % 3 != 0) {
            return "Gene Length is not mod of 3";
        }
        
        return Gene;
    }

    public static void testSimpleGene() {
        // DNA with no "ATG"
        String DNA = "CTGTAA";
        String result = findSimpleGene(DNA);
        System.err.println(result);

        // DNA with no “TAA”
        String DNA2 = "ATGCTG";
        String result2 = findSimpleGene(DNA2);
        System.err.println(result2);

        // DNA with no “ATG” or “TAA”;


        // DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene)
        String DNA4 = "ATGATGTAA";
        String result4 = findSimpleGene(DNA4);
        System.err.println(result4);

        // DNA with ATG, TAA and the substring between them is not a multiple of 3
        String DNA5 = "ATGTGTAA";
        String result5 = findSimpleGene(DNA5);
        System.err.println(result5);

        //Test
        String DNA6 = "AAATGCCCTAACTAGATTAAGAAACC";
        String result6 = findSimpleGene(DNA6);
        System.err.println(result6);
    }

    public static void main(String[] args) {
        testSimpleGene();
    }
}
