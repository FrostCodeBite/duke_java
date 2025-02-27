import java.util.ArrayList;
import java.util.Random;

public class MarkovOne {
    private String myText;
	private Random myRandom;
	
	public MarkovOne() {
		myRandom = new Random();
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
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index+1);
        sb.append(key);

		for(int k=0; k < numChars-1; k++){
			// int index = myRandom.nextInt(myText.length());
			// sb.append(myText.charAt(index));
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
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
