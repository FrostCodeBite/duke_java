package CaesarCipher;

public class CaesarCipherOne {
    private String alphabet;
    private String shiftedAlphabet; 

    private int mainKey;

    public CaesarCipherOne() {}

    public CaesarCipherOne(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }

    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);

        for (int i = 0; i < encrypted.length(); i++) {
            
            char currChar = encrypted.charAt(i);
            int index = alphabet.indexOf(currChar);

            if (index == -1) {
                index = alphabet.toLowerCase().indexOf(currChar);
            }

            if (index != -1) {
                if (Character.isLowerCase(currChar) == true) {
                    char newChar = shiftedAlphabet.charAt(index);
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                } else {
                    char newChar = shiftedAlphabet.charAt(index);
                    encrypted.setCharAt(i, newChar);
                }
                
            }
        }

        return encrypted.toString();
    }

    public String decrypt(String input) {
        CaesarCipherOne cc = new CaesarCipherOne(26 - mainKey);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
}
