package GladLibs;

import java.util.ArrayList;

import edu.duke.FileResource;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {
        
        if (myWords != null || myFreqs != null) {
            myWords.clear();
            myFreqs.clear();
        }

        FileResource fr = new FileResource();

        for (String word : fr.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            
            if (!myWords.contains(word)) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq+1);

            }
        }
        for (int i = 0; i < myWords.size(); i++) {
            System.err.println("Word "+i+ " : "+myWords.get(i) + ", Number: "+myFreqs.get(i));
        }
    }

    public void tester() {
        findUnique();
        findIndexOfMax();
    }

    public int findIndexOfMax() {
        int maxIndex = 0;
        String word = "";

        for (int i = 0; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > maxIndex) {
                word = myWords.get(i);
                
                maxIndex = myFreqs.get(i);
            }
        }

        System.err.println("Number of unique words: "+myWords.size());

        System.err.println("The word that occurs most often and its count are: \""+word+"\" "+maxIndex);

        return maxIndex;
    }

    public static void main(String[] args) {
        WordFrequencies obj = new WordFrequencies();
        obj.tester();
    }
}
