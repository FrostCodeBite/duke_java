package CaesarCipher;

import edu.duke.FileResource;

public class CaesarBreaker {

    public static int[] countLetters(String encrypted) {

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] freq = new int[26];

        for (int i = 0; i < encrypted.length(); i++ ) {
            char currLetter = encrypted.toLowerCase().charAt(i);

            int alpIndex = alphabet.indexOf(String.valueOf(currLetter));

            if (alpIndex != -1) {
                freq[alpIndex]++;
            }
        }

        return freq;
    }

    public static int maxIndex(int[] freq) {
        int maxIndex = 0;

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > freq[maxIndex]) {
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    public static String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] frequency = countLetters(encrypted);
        int maxIndex = maxIndex(frequency);
        int dKey = maxIndex - 4; 
        if (maxIndex < 4) {
            dKey = 26 - (4-maxIndex);
        }

        String message = cc.encrypt(encrypted, 26 - dKey);
        System.err.println("Decrypted Key: "+(26-dKey));
        return message;
    }

    public static void testDecrypt() {
        FileResource fr = new FileResource();
        CaesarCipher cc = new CaesarCipher();

        String text = "";
        for (String word: fr.words()) {
            text += word + " ";
        }

        System.err.println("Original Text: "+ text);
        String encrypted = cc.encrypt(text, 5);
        System.err.println("Encrypted Text: "+encrypted);

        String message = decrypt(encrypted);
        System.err.println("Decrypted Text: "+ message);
    }

    //Part2

    public static String halfOfString(String message, int start) {
        
        String halfString = "";

        for (int i = start; i < message.length(); i+=2){
            char currChar = message.charAt(i);
            halfString += String.valueOf(currChar);
        }

        return halfString;
    }

    public static void testHalfOfString() {
        
        String halfString = halfOfString("Qbkm Zgis", 0) ;
        System.err.println(halfString);
    }

    //Part3

    public static int getKey(String s) {
        int[] frequency = countLetters(s);
        int maxIndex = maxIndex(frequency);
        int dKey = maxIndex - 4; 
        if (maxIndex < 4) {
            dKey = 26 - (4 - maxIndex);
        }

        return dKey;
    }

    public static String decryptTwoKeys(String encrypted) {

        CaesarCipher cc = new CaesarCipher();
        
        String halfString0 = halfOfString(encrypted, 0);
        String halfString1 = halfOfString(encrypted, 1);

        System.err.println("Decrypted Half 1: "+halfString0 +" , Decrypted Half 2: "+ " "+halfString1);

        int key1 = getKey(halfString0);
        int key2 = getKey(halfString1);

        System.err.println("Decrypted Key 1: "+key1 +" , Decrypted Key 2: "+ " "+key2);

        String message = cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
        return message;
    }

    public static void testDecryptTwoKeys() {
        CaesarCipher cc = new CaesarCipher();
        String text = "Top ncmy qkff vi vguv vbg ycpx";
        String encripted = cc.encryptTwoKeys(text, 2, 20);
        System.err.println("Encrypted Message: "+encripted);
        String decrypted = decryptTwoKeys(encripted);
        System.err.println("Decrypted Message: "+decrypted);
    }

    public static void main(String[] args) {
        // testDecrypt();
        // testHalfOfString();
        testDecryptTwoKeys();
        /* Comment: sometimes the results from decryption methods will not yield 
        good results as it is based on most common letter of alphabet to decrypte
        the message back to origin */
    }
}
