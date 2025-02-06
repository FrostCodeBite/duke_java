public class Part3 {

    public static boolean twoOccurrences(String stringa, String stringb) {
            
        String tempString = stringb.replaceAll(stringa, "");
        int count = (stringb.length() - tempString.length())/stringa.length();

        if (count >= 2) {
            return true;
        } else {
            return false;
        }
    }

    public static String lastPart(String stringa, String stringb) {
        
        int index = stringb.indexOf(stringa);
        if (index ==  -1) {
            return "No";
        }

        String last = stringb.substring(index);

        return last;
    }

    public static void testing() {
        boolean result = twoOccurrences("by", "A story by Abby Long");
        System.err.println(result);

        boolean result2 = twoOccurrences("a", "banana");
        System.err.println(result2);

        boolean result3 = twoOccurrences("atg", "ctgtatgta"); 
        System.err.println(result3);

        String result4 = lastPart("an", "banana"); 
        System.err.println(result4);

        String result5 = lastPart("zoo", "forest");
        System.err.println(result5);
    }
    
    public static void main(String[] args) {
        testing();
    }
}
