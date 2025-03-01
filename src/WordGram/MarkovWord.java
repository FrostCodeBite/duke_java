import java.util.ArrayList;
import java.util.Random;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
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

}
