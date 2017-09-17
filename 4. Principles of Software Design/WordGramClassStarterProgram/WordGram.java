
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
        // TODO: Complete this method
        return myWords.length;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        // TODO: Complete this method
        for(int i = 0; i < myWords.length; i++)
        {
            sb.append(myWords[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        for(int i= 0; i < myWords.length; i++)
        {
            if(!myWords[i].equals(other.myWords[i]))
            {
                return false;
            }
        }
        return true;

    }

    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        for(int i = 0; i < myWords.length; i++)
        {
            if(i == myWords.length-1)
            {
                out.myWords[myWords.length-1] = word;
            }
            else
            {
                out.myWords[i] = myWords[i+1];
            }
        }
        return out;
    }
    
    public int hashCode()
    {
        return this.toString().hashCode();
    }

}