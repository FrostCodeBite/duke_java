package GladLibs;

import java.util.ArrayList;

import edu.duke.FileResource;

public class CharactersInPlay {
    private static ArrayList<String> characterList = new ArrayList<String>();
    private static ArrayList<Integer> characterCount = new ArrayList<Integer>();

    public static void update(String person) {

        int index = characterList.indexOf(person);

        // System.err.println(characterList.size());
        
        if (!characterList.contains(person)) {
            characterList.add(person);
            characterCount.add(1);
        } else {
            int freq = characterCount.get(index);
            characterCount.set(index, freq+1);
        }
    }

    public static void findAllCharacters() {

        if (characterList != null || characterCount != null) {
            characterList.clear();
            characterCount.clear();
        }

        FileResource fr = new FileResource();

        for (String word : fr.lines()) {
            int dotIndex = word.indexOf(".");

            if (dotIndex != -1) {
                String charName = word.substring(0, dotIndex);
                // System.err.println(charName);
                update(charName);
            }
        }
    }

    public static void charactersWithNumParts(int num1, int num2) {

        for (int i = 0; i < characterList.size(); i++) {
            if (characterCount.get(i) >= num1 && characterCount.get(i) <= num2) {
                System.err.println("Name: "+ characterList.get(i)+", Appear: "+characterCount.get(i));
            }
        }  
    }

    public static void tester() {
        findAllCharacters();
        charactersWithNumParts(10, 15);
        
    }

    public static void main(String[] args) {
        tester();
    }

}
