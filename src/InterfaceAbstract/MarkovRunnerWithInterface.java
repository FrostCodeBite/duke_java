
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
		long startTime = System.nanoTime();
        markov.setTraining(text);
        // System.out.println("running with " + markov);
		markov.setRandom(seed);
		System.err.println(markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
		long endTime = System.nanoTime();
		System.out.println("Program took " + (endTime - startTime) + " nanoseconds") ;
    }

    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 1024;
		
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        // MarkovOne mOne = new MarkovOne();
        // runModel(mOne, st, size, seed);
        
        // MarkovModel mThree = new MarkovModel(3);
        // runModel(mThree, st, size, seed);
        
        // MarkovFour mFour = new MarkovFour();
        // runModel(mFour, st, size, seed);

		

    }

	public void testHashMap() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		// String st = "yes-this-is-a-thin-pretty-pink-thistle";
		int size = 200;
		int seed = 531;

		EfficientMarkovModel eModel = new EfficientMarkovModel(5);
		runModel(eModel, st, size, seed);
		eModel.printHashMapInfo();
	}

	public void compareMethods() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 50;
		int seed = 615;

		// MarkovModel mThree = new MarkovModel(2);
        // runModel(mThree, st, size, seed);
		

		EfficientMarkovModel eModel = new EfficientMarkovModel(5);
		// runModel(eModel, st, size, seed);
		eModel.setTraining(st);
		eModel.setRandom(seed);
		eModel.printHashMapInfo();
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
		MarkovRunnerWithInterface obj = new MarkovRunnerWithInterface();
		// obj.runMarkov();
		obj.testHashMap();
		// obj.compareMethods();
	}
	
}
