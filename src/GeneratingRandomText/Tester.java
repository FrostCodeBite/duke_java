import java.util.ArrayList;

import edu.duke.FileResource;

public class Tester {

    public void testGetFollows() {
        String st = "this is a test yes this is a test.";
		MarkovOne markov = new MarkovOne();
		markov.setTraining(st);
		ArrayList<String> list = markov.getFollows(".");
		for (int i = 0; i < list.size(); i++) {
			System.err.println(i+" : "+list.get(i));
		}
    }

    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
        // String st = "this is a test yes this is a test";
		MarkovModel markov = new MarkovModel(1);
		markov.setTraining(st);
        ArrayList<String> list = markov.getFollows("o");
		for (int i = 0; i < list.size(); i++) {
			System.err.println(i+" : "+list.get(i));
		}
        System.err.println("Total number of list: "+list.size());
    }

    public static void main(String[] args) {
        Tester obj = new Tester();
        // obj.testGetFollows();
        obj.testGetFollowsWithFile();
    }
}
