package GladLibs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;

public class WordsInFiles {
    private HashMap<String, String> fileHashMap;

    public WordsInFiles() {
        fileHashMap = new HashMap<String, String>();
    }

    public HashMap<String, String> getFileHashMap() {
        return fileHashMap;
    }

    public void addWordsFromFile(File f) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                for (String word : line.split("\\s+")) {
                    if (!fileHashMap.containsKey(word)) {
                        fileHashMap.put(word, f.getName());
                    } else {
                        String file = fileHashMap.get(word);
                        if (!file.contains(f.getName())) {
                            fileHashMap.put(word, file +", "+f.getName());
                            // System.err.println(word+" : "+fileHashMap.get(word));
                        }     
                    }
                }
            }
         
             br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int maxNumber() {
        int maxNumber = 0;

        for (String word: fileHashMap.keySet()) {
            int count = 1;

            String fileList = fileHashMap.get(word);
            for (int i = 0; i < fileList.length(); i++)
            { 
                if(fileList.charAt(i) == ',')
                {
                    count++;
                }
            }

            if (maxNumber < count) {
                maxNumber = count;
            }
        }
        return maxNumber;
    }

    public ArrayList<String> wordsInNumFiles(int number) {

        ArrayList<String> wordArrayList = new ArrayList<String>();

        for (String word: fileHashMap.keySet()) {
            int count = 1;

            String fileList = fileHashMap.get(word);
            for (int i = 0; i < fileList.length(); i++)
            { 
                if(fileList.charAt(i) == ',')
                {
                    count++;
                }
            }

            if (count == number) {
                wordArrayList.add(word);
            }
        }

        return wordArrayList;
    }

    public void printFilesIn(String word) {
        for (String w: fileHashMap.keySet()) {
            if (w.equals(word)) {
                String file = fileHashMap.get(w);
                System.err.println("\""+word+"\"" +" appear in files: "+file);
            }
        }
    }

    public void buildWordFileMap() {
        fileHashMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
        // for (String word: fileHashMap.keySet()) {
        //     System.err.println("\""+word+"\"" + " appears in the files: "+fileHashMap.get(word));
        // }

        // int maxNumber = maxNumber();
        // System.err.println("Max Number of files: "+maxNumber);

        // int num = 4;
        // ArrayList<String> list = wordsInNumFiles(num);
        // System.err.println("Words in numbers of file: "+num);
        // for (int i = 0; i < list.size(); i++) {
        //     System.err.println(list.get(i));
        // }
        // System.err.println("Number of words that appear in "+num+" files : " +list.size());

        printFilesIn("tree");
    }

    public void tester() {
        buildWordFileMap();
    }

    public static void main(String[] args) {
        WordsInFiles object = new WordsInFiles();
        object.tester();
        
    }



    
}
