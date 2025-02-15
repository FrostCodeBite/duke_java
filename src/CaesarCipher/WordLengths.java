package CaesarCipher;

import edu.duke.FileResource;

public class WordLengths {

    public static String[] countWordLengths(FileResource resource, int[] counts) {

        String[] wordArray = new String[counts.length];
        for (String word: resource.words()) {

            String newWord = word;

            int wordCount = word.length();
            

            if (Character.isLetter(word.charAt(0)) == false) {
                wordCount--;
                newWord = word.substring(1, word.length());
            } 

            if (wordCount == 0) {
                return wordArray;
            }

            if (Character.isLetter(word.charAt(word.length()-1)) == false) {
                wordCount--;
                newWord = newWord.substring(0, newWord.length()-1);
            }

            counts[wordCount]++;
            if (wordArray[wordCount] == null) {
                wordArray[wordCount] = "";
            }
            wordArray[wordCount] = wordArray[wordCount] +" "+ newWord ;
            
        }

        return wordArray;
    }

    public static void testCountWordLengths() {
        FileResource fr = new FileResource(); 
        int[] counts = new int[50];
        String[] word = countWordLengths(fr, counts);
        for (int i = 0; i < 31; i++) {
            if (counts[i] != 0 && word[i] != null) {
                System.out.println(counts[i] + " words of length " + i+" :");
            }
        }

        System.err.println(indexOfMax(counts));
    }

    public static int indexOfMax(int[] values) {
        int maxIndex = 0;

        for (int i = 0; i < values.length; i++) {
            if (values[i] > maxIndex) {
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    public static void main(String[] args) {
        testCountWordLengths();
    }
}
