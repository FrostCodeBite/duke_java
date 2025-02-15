package CaesarCipher;

public class TestCaesarCipherTwo {

    private static int mainKey1;
    private static int mainKey2;
    
    private static String halfOfString(String message, int start) {
        
        String halfString = "";

        for (int i = start; i < message.length(); i+=2){
            char currChar = message.charAt(i);
            halfString += String.valueOf(currChar);
        }

        return halfString;
    }

    private static int[] countLetters(String encrypted) {

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

    private static int maxIndex(int[] freq) {
        int maxIndex = 0;

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > freq[maxIndex]) {
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    private static int getKey(String s) {
        int[] frequency = countLetters(s);
        int maxIndex = maxIndex(frequency);
        int dKey = maxIndex - 4; 
        if (maxIndex < 4) {
            dKey = 26 - (4 - maxIndex);
        }

        return dKey;
    }

    private static void breakCaesarCipher(String input) {

        String halfString1 = halfOfString(input, 0);
        String halfString2 = halfOfString(input, 1);

        System.err.println("Decrypted Half 1: "+halfString1 +" , Decrypted Half 2: "+ " "+halfString2);

        mainKey1 = getKey(halfString1);
        mainKey2 = getKey(halfString2);

    }

    public static void simpleTests() {
        String message = "Eren and Emily have evil eerie green ears";

        CaesarCipherTwo cc = new CaesarCipherTwo(17, 3);
        String encrypted = cc.encrypt(message);
        System.err.println("Encrypted Message: "+encrypted);

        breakCaesarCipher(encrypted);
        System.err.println("Key 1: "+mainKey1+" , Key 2: "+mainKey2);

        CaesarCipherTwo cc2 = new CaesarCipherTwo(mainKey1,mainKey2);
        String decrypted = cc2.decrypt(encrypted);
        System.err.println("Decrypted Message: "+decrypted);
    }

    public static void main(String[] args) {
        simpleTests();
    }
}
