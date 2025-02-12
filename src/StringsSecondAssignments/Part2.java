package StringsSecondAssignments;

public class Part2 {

    public static int howMany(String stringa, String stringb) {

        String tempString = stringb.replaceAll(stringa, "");
        int count = (stringb.length() - tempString.length())/stringa.length();
        
        return count;
    }

    public static void testHowMany() {
        int time = howMany("GAA", "ATGAACGAATTGAATC");
        System.err.println(time);

        int time2 = howMany("AA", "ATAAAA");
        System.err.println(time2);
    }

    public static void main(String[] args) {
        testHowMany();
        
    }
}
