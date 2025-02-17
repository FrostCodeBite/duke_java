package GladLibs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import edu.duke.FileResource;
import edu.duke.URLResource;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private Random myRandom;
    private ArrayList<String> duplicateList;

    private String dataSourceDirectory = "data";

    public GladLibMap() {
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        duplicateList = new ArrayList<String>();
    } 

    public GladLibMap(String source) {
        initializeFromSource(source);
        myRandom = new Random();
        duplicateList = new ArrayList<String>();
    } 

    private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}

    public void initializeFromSource(String source) {

        myMap = new HashMap<String, ArrayList<String>>();

        String[] categories = {"adjective", "noun", "color", "country", 
                                "name", "animal", "timeframe", "verb", "fruit"};

        for (int i = 0; i < categories.length; i++) {

            ArrayList<String> wordArrayList = readIt(source+"/"+categories[i]+".txt");
            String category = categories[i];
            myMap.put(category, wordArrayList);
        }
    }

    private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}

    private String getSubstitute(String label) {

		if (label.equals("number")) {
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
	}

    private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}

		if (!duplicateList.contains(w)) {
			String prefix = w.substring(0,first);
			String suffix = w.substring(last+1);
			String sub = getSubstitute(w.substring(first+1,last));
			return prefix+sub+suffix;
		} else {
			return "DUPLICATE";
		}
	}

    private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}

    private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}

    public void makeStory(){
        duplicateList.clear();
	    System.out.println("\n");
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);
	}

    public static void main(String[] args) {
        GladLibMap obj = new GladLibMap();
        obj.makeStory();

        int totalWords = obj.totalWordsInMap();
        System.err.println("\n\ntotal number of words: "+totalWords);


        int totalConsidered = obj.totalWordsConsidered();
        System.err.println("\ntotal number of words considered: "+totalConsidered);
    }

    public int totalWordsInMap() {
        int totalWords = 0;

        for (String w: myMap.keySet()) {
            ArrayList<String> arr = myMap.get(w);
            totalWords += arr.size();
        }

        return totalWords;
    }

    public int totalWordsConsidered() {
        int totalWords = 0;

        ArrayList<String> al = new ArrayList<String>();
        FileResource fr = new FileResource("data/madtemplate2.txt");
        for (String s: fr.words()){
            if (s != processWord(s)){
                String a = s.substring(s.indexOf("<") + 1, s.indexOf(">"));
                if (a == "number"){
                    continue;
                }
                if (al.contains(a) == false){
                    al.add(a);
                }
            }
        }
        for (String b: al){
            int size = 0;
            try {
                size = myMap.get(b).size();
            } catch (Exception e) {
                // TODO: handle exception
            }
            totalWords += size;
        }
        return totalWords;
    }
}
