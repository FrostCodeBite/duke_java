import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int nChara;
    private HashMap<String, ArrayList<String>> hm; 

    public EfficientMarkovModel(int nCharacter) {
            super(nCharacter);
            nChara = nCharacter;
            hm = new HashMap<String, ArrayList<String>>();
            //TODO Auto-generated constructor stub
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

    public void buildMap() {

        for (int i = 0; i < myText.length()-num; i++) {
            String key = myText.substring(i, i + nChara);
            String follow = getFollowingCharacter(i);

            if (hm.containsKey(key)) {
                hm.get(key).add(follow);
            } else {
                ArrayList<String> list = new ArrayList<String>();
				list.add(follow);
                hm.put(key, list);
            }
            
        }
    }

    public void printHashMapInfo() {
        buildMap();
        int largetNum = 0;
        String largestKey = "";
        for (String key : hm.keySet()) {
            int currentKeySize = hm.get(key).size();

            if (currentKeySize > largetNum) {
                largetNum = currentKeySize;
                largestKey = key;
            }
            System.err.println(key+" : "+hm.get(key));
        }
        System.err.println("The total number of keys in the HashMap are: "+hm.size()+1);
        System.err.println("\""+largestKey+"\" is the key that have the maximum size value with size of "+largetNum);
    }

    @Override
    public String toString() {
        return "EfficientMarkovModel of the " + nChara;
    }

    
}
