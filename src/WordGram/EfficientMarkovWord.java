import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> hm; 

    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        hm = new HashMap<WordGram, ArrayList<String>>(); 
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}

    public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText,index,myOrder);
		sb.append(key.toString());
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = key.shiftAdd(next);

		}
		
		return sb.toString().trim();
	}

    private ArrayList<String> getFollows(WordGram key) {
	    ArrayList<String> follows = new ArrayList<String>();
		int textLength = myText.length;

		for (int i = 0; i < textLength-key.length(); i++) {
            WordGram currWg = new WordGram(myText,i,myOrder);
			if (key.equals(currWg)) {
				String nextWord = myText[i+myOrder];
				follows.add(nextWord);
			}
		}
	    return follows;
    }


    public void buildMap() {
        int textLength = myText.length-myOrder;

        for (int i = 0; i <= textLength; i++) {
            WordGram currWg = new WordGram(myText,i,myOrder);
            String follow = "";
            try {
                follow = myText[i+currWg.length()];
            } catch (Exception e) {}
            

            if (hm.containsKey(currWg)) {
                hm.get(currWg).add(follow);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(follow);
                hm.put(currWg, list);
            }
        }
    }

    public void printHashMapInfo() {
        buildMap();
        int largetNum = 0;
        WordGram largestKey = new WordGram(myText, largetNum, largetNum);
        for (WordGram key : hm.keySet()) {
            int currentKeySize = hm.get(key).size();

            if (currentKeySize > largetNum) {
                largetNum = currentKeySize;
                largestKey = key;
            }
            System.err.println(key+" : "+hm.get(key));
        }
        System.err.println("The total number of keys in the HashMap are: "+hm.size());
        System.err.println("\""+largestKey+"\" is the key that have the maximum size value with size of "+largetNum);
    }
}
