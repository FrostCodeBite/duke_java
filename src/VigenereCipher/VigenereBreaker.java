package VigenereCipher;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class VigenereBreaker {

    public VigenereBreaker() { }

    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();

        for (int i = whichSlice; i < message.length(); i = i+totalSlices) {
            sb.append(message.charAt(i));
        }

        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];

        for (int i = 0; i < klength; i++) {
            String message = sliceString(encrypted, i, klength);

            int mKey = new CaesarCracker().getKey(message);
            key[i] = mKey;
        }

        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        /* WHEN KEY IS PROVIDED */
            // int[] key = tryKeyLength(message, 4, 'e');
            // VigenereCipher vc = new VigenereCipher(key);
            // String output = vc.decrypt(message);
        // System.err.println(output);

        /* WHEN KEY IS NOT PROVIDED */
        // FileResource fr2 = new FileResource();
        // HashSet<String> dict = readDictionary(fr2);
        // int wordNum = countWords(output, dict);
        // System.err.println(wordNum);
        // String decrypted = breakForLanguage(message, dict, 'e');
        // System.err.println(decrypted);

        /* BREAK IN MULTIPLE LANGUAGE */
        // char commonChar = mostCommonCharIn(dict);
        // System.err.println(commonChar);

        HashMap<String, HashSet<String>> langHashMap = new HashMap<String, HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr2 = new FileResource(f);
            HashSet<String> dictFile = readDictionary(fr2);
            langHashMap.put(f.getName(), dictFile);
        }
        breakForAllLangs(message, langHashMap);
        
    }

    public static void main(String[] args) {
        VigenereBreaker obj = new VigenereBreaker();
        /* SLICING MESSAGE BASED ON KEYS */
        // String message = "abcdefghijklm";
        // String newMessage = obj.sliceString(message, 0, 3);
        // System.err.println(newMessage);

        /* PRINT OUT POSSIBLE KEYS */
        // FileResource fr = new FileResource();
        // String message2 = fr.asString();
        // int[] key = obj.tryKeyLength(message2, 4, 'e');
        // System.err.println(Arrays.toString(key));

        /* TEST BREAKING VIGENERE */
        obj.breakVigenere();
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> hs = new HashSet<String>();

        for (String line : fr.lines()) {
            line = line.toLowerCase();
            hs.add(line);
        }

        return hs;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;

        String[] wordList = message.split("\\W");

        for (String word : wordList) {
            if (dictionary.contains(word)) {
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary, char commonChar) {
        int maxWord = 0;
        int mKey = 0;
        String dMessage = "";

        for (int i = 0; i < 100; i++) {
            int[] keys = tryKeyLength(encrypted, i, commonChar);
            if (keys.length != 0) {
                VigenereCipher vc = new VigenereCipher(keys);
                String output = vc.decrypt(encrypted);

                int wordNum = countWords(output.toLowerCase(), dictionary);
                if (wordNum > maxWord) {
                    maxWord = wordNum;
                    mKey = i; 
                    dMessage = output;
                }
            }
        }

        String firstline = dMessage.substring(0, dMessage.indexOf("\n"));
        System.err.println("\n\n"+firstline);
        System.err.println("This file contain valid words:"+maxWord+" at key: "+mKey);

        return dMessage;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        char commonChar = ' ';
        int maxFreq = 0;

        HashMap<Character, Integer> charHashMap = new HashMap<Character, Integer>();

        for (String word: dictionary) {
            for (int i = 0; i < word.length(); i++) {
                char currChar = word.charAt(i);
                if (!charHashMap.containsKey(currChar)) {
                    charHashMap.put(currChar, 1);
                } else {
                    int freq = charHashMap.get(currChar);
                    charHashMap.put(currChar, freq+1);
                }
            }
        }

        for (Character c: charHashMap.keySet()) {
            int charValues = charHashMap.get(c);
            if (charValues > maxFreq) {
                maxFreq = charValues;
                commonChar = c;
            }
        }

        return commonChar;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int max = 0;
        char c = ' ';
        HashSet<String> dict = new HashSet<String>();
        String file = "";
        
        for (String filename : languages.keySet()) {
            HashSet<String> dictionary = languages.get(filename);
            char commonChar = mostCommonCharIn(dictionary);

            int countWord = countWords(encrypted.toLowerCase(), dictionary);
            if (countWord > max) {
                file = filename;
                max = countWord;
                c = commonChar;
                dict = dictionary;    
            }

        }

        String decrypted = breakForLanguage(encrypted, dict, c);
        System.err.println(decrypted);

        String firstline = decrypted.substring(0, decrypted.indexOf("\n"));
        System.err.println("\n\n"+firstline);

        System.err.println("File for decryption is: "+file+" with common char: "+c);
        System.err.println("The most valid words are: "+max);
    }
}
