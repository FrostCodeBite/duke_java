import java.util.ArrayList;
import java.util.Random;

public class MarkovWordTwo implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-2);  // random word to start with
		String key1 = myText[index];
        String key2 = myText[index+1];
		sb.append(key1);
		sb.append(" ");
        sb.append(key2);
        sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key1, key2);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key1 = key2;
            key2 = next;
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key1, String key2) {
	    ArrayList<String> follows = new ArrayList<String>();
		int textLength = myText.length;

		for (int i = 0; i < textLength-key2.length(); i++) {
			String currWord1 = myText[i];
            String currWord2 = myText[i+1];

            if (key1.equals(currWord1) && key2.equals(currWord2)) {
                String nextWord = myText[i+2];
				follows.add(nextWord);
            }
			
		}
	    return follows;
    }
}
