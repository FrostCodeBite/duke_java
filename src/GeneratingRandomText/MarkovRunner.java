
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunner {
    public void runMarkovZero() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovZero markov = new MarkovZero();
		markov.setRandom(1024);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(200);
			printOut(text);
		}
	}

	public void runMarkovOne() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovOne markov = new MarkovOne();
		markov.setTraining(st);
		markov.setRandom(365);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(200);
			printOut(text);
		}
	}

	public void runMarkovFour() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovFour markov = new MarkovFour();
		markov.setTraining(st);
		markov.setRandom(715);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(200);
			printOut(text);
		}
	}

	public void runMarkovModel() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovModel markov = new MarkovModel(7);
		markov.setTraining(st);
		markov.setRandom(953);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(200);
			printOut(text);
		}
	}
	
	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

	public static void main(String[] args) {
		MarkovRunner obj = new MarkovRunner();
		// obj.runMarkovZero();
		// obj.runMarkovOne();
		// obj.runMarkovFour();
		obj.runMarkovModel();
	}
	
}
