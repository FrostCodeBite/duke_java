package CaesarCipher;

public class TestCaesarCipherOne {

    private static int mainKey;

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

    private static void breakCaesarCipher(String input) {
        
        int[] frequency = countLetters(input);
        int maxIndex = maxIndex(frequency);
        int dKey = maxIndex - 4; 
        if (maxIndex < 4) {
            dKey = 26 - (4-maxIndex);
        }

        mainKey = dKey;
    }

    public static void simpleTests() {
        String message = "Include private fields for the alphabet, shiftedAlphabet1, and shiftedAlphabet2.";
        
        CaesarCipherOne cc = new CaesarCipherOne(7);
        String encrypted = cc.encrypt(message);
        System.err.println("Encrypted Message: "+encrypted);

        breakCaesarCipher(encrypted);
        System.err.println("Key: "+mainKey);

        CaesarCipherOne cc2 = new CaesarCipherOne(mainKey);
        String decrypted = cc2.decrypt(encrypted);
        System.err.println("Decrypted Message: "+decrypted);
    }

    public static void main(String[] args) {
        simpleTests();
    }
    
    
}
