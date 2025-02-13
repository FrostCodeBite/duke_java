package ImplementingCaesarCipher;

public class WordPlay {

    public static boolean isVowel(char ch) {

        if (String.valueOf(ch).toLowerCase().matches(".*[aeiou].*")) {
            return true;
        } else {
            return false;
        }
    }

    public static void testIsVowel() {
        boolean result = isVowel('F');
        System.err.println(result);
        boolean result2 = isVowel('a');
        System.err.println(result2);
    }

    //Part 2

    public static String replaceVowels(String phrase, char ch) {

        String newText = "";
        
        for (int i = 0; i < phrase.length(); i++) {
            char currChar = phrase.charAt(i);
            boolean isVowel = isVowel(currChar);

            if (isVowel) {
                newText += "*";
            } else {
                newText += String.valueOf(currChar);
            }
        }

        return newText;

    }

    public static void testReplaceVowels() {
        String result = replaceVowels("Hello World", '*');
        System.err.println(result);
    }

    //Part 3

    public static String emphasize(String phrase, char ch) {
        String newText = "";
        
        for (int i = 0; i < phrase.length(); i++) {

            char currChar = phrase.charAt(i);

            if (String.valueOf(currChar).equals(String.valueOf(ch))) {
                if (i%2 == 0) {
                    newText += "*";
                } else {
                    newText += "+";
                }
            } else {
                newText += String.valueOf(currChar);
            }
        }

        return newText;
    }

    public static void testEmphasize() {
        String result = emphasize("dna ctgaaactga", 'a');
        System.err.println(result);
        String result2 = emphasize("Mary Bella Abracadabra", 'a');
        System.err.println(result2);
    }

    public static void main(String[] args) {
        // testIsVowel();
        // testReplaceVowels();
        testEmphasize();
    }
}
