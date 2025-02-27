import java.util.ArrayList;

public class MarkovModel extends AbstractMarkovModel{
    private int nChara;

    public MarkovModel(int nCharacter) {
            super(nCharacter);
            nChara = nCharacter;
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
}
