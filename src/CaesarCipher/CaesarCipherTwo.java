package CaesarCipher;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;

    private int mainKey1;
    private int mainKey2;

    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        mainKey1 = key1;

        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey2 = key2;
    }

    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
         
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            int index = alphabet.indexOf(currChar);

            if (index == -1) {
                index = alphabet.toLowerCase().indexOf(currChar);
            }

            if (i%2 == 0) {
                if (index != -1) {
                    if (Character.isLowerCase(currChar) == true) {
                        char newChar = shiftedAlphabet1.charAt(index);
                        encrypted.setCharAt(i, Character.toLowerCase(newChar));
                    } else {
                        char newChar = shiftedAlphabet1.charAt(index);
                        encrypted.setCharAt(i, newChar);
                    }    
                }
            } else {
                if (index != -1) {
                    if (Character.isLowerCase(currChar) == true) {
                        char newChar = shiftedAlphabet2.charAt(index);
                        encrypted.setCharAt(i, Character.toLowerCase(newChar));
                    } else {
                        char newChar = shiftedAlphabet2.charAt(index);
                        encrypted.setCharAt(i, newChar);
                    }  
                }
            }
        }

        return encrypted.toString();
        
    }

    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
}
