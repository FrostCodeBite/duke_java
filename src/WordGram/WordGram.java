
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){

        return myWords.length;
    }

    public String toString(){
        String ret = "";
        // TODO: Complete this method
        for (int i = 0; i < myWords.length; i++) {
            ret += myWords[i]+" ";
        }

        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if (this.length() != other.length()) {
            return false;
        }
        for (int i = 0; i < myWords.length; i++) {
            if (!myWords[i].equals(other.wordAt(i))) {
                return false;
            }
        }
        return true;

    }

    public WordGram shiftAdd(String word) {	
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        for (int i = 0; i < myWords.length-1; i++) {
            myWords[i] = this.wordAt(i+1);
        }
        myWords[myWords.length-1] = word;
        WordGram out = new WordGram(myWords, 0, myWords.length);
        return out;
    }

    public int hashCode() {
        int code = 0;
        code = this.toString().hashCode();
        return code;
    }

}