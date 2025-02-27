
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int num;
    
    public AbstractMarkovModel(int number) {
        myRandom = new Random();
        num = number;
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setRandom(int seed){
		myRandom = new Random(seed);
	}
 
    abstract public String getRandomText(int numChars);

    public String getFollowingCharacter(int pos) {
        int index = pos+num;
		return myText.substring(index, index+1);
    }
    
    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> charList = new ArrayList<String>();
        int length = myText.length()-num;

        for (int i = 0; i < length; i++) {
            String currChar = myText.substring(i, i+num);
            String last = getFollowingCharacter(i);
            if (key.equals(currChar)) {
                charList.add(last);
            }
            
        }

        return charList;
    }

    @Override
    public String toString() {
        return "MarkovModel of order " + num;
    }
}
