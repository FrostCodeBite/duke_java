import java.util.ArrayList;
import java.util.Random;

public class MarkovModel {
    private String myText;
	private Random myRandom;
    private int nChara;
	
	public MarkovModel(int nCharacter) {
		myRandom = new Random();
        nChara = nCharacter;
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-nChara);
        String key = myText.substring(index, index+nChara);
        sb.append(key);

		for(int k=0; k < numChars-nChara; k++){
			// int index = myRandom.nextInt(myText.length());
			// sb.append(myText.charAt(index));
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1)+next;
		}
		
		return sb.toString();
	}

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> charList = new ArrayList<String>();
        int currIndex = 0;

        for (int i = currIndex; i < myText.length(); i = i++) {
            currIndex = myText.indexOf(key,currIndex);
            if (currIndex == -1) {
                break;
            }
            String last = " ";
            try {
                last = String.valueOf(myText.charAt(currIndex+key.length()));
            } catch (Exception e) {
                // TODO: handle exception
            }
            
            charList.add(last);
            currIndex++;
        }

        return charList;
    }
}
