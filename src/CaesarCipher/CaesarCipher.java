package CaesarCipher;

import edu.duke.FileResource;

public class CaesarCipher {

    public static String encrypt(String input, int key) {
        
        StringBuilder encrypted = new StringBuilder(input);

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabelt = alphabet.substring(key) + alphabet.substring(0, key);

        for (int i = 0; i < encrypted.length(); i++) {
            
            char currChar = encrypted.charAt(i);
            int index = alphabet.indexOf(currChar);

            if (index == -1) {
                index = alphabet.toLowerCase().indexOf(currChar);
            }

            if (index != -1) {
                if (Character.isLowerCase(currChar) == true) {
                    char newChar = shiftedAlphabelt.charAt(index);
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                } else {
                    char newChar = shiftedAlphabelt.charAt(index);
                    encrypted.setCharAt(i, newChar);
                }
                
            }
        }

        return encrypted.toString();
    }

    public static void testEncrypt() {
        String result = encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15);
        System.err.println(result);
        String result2 = encrypt("First Legion", 23);
        System.err.println(result2);
        String result3 = encrypt("First Legion", 17);
        System.err.println(result3);
    }

    //Part 2

    public static void testCaesar() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 15;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }

    //Part3

    public static String encryptTwoKeys(String input, int key1, int key2) {
        
        String newText = "";
         
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);

            if (i%2 == 0) {
                newText += encrypt(String.valueOf(currChar), key1);
            } else {
                newText += encrypt(String.valueOf(currChar), key2);
            }
        }

        return newText;
    }

    public static void testEncryptTwoKeys() {
        String result = encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.", 14, 24);
        System.err.println(result);
    }

    public static void main(String[] args) {
        // testEncrypt();
        // testCaesar();
        testEncryptTwoKeys();
    }

}
