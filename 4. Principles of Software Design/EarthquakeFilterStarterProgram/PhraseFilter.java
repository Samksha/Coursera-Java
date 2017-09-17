
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter
{
    private String where;
    private String phrase;
    
    public PhraseFilter(String a, String b)
    {
        where = a;
        phrase = b;
    }
    
    public boolean satisfies(QuakeEntry qe)
    {
        String s = qe.getInfo();
        int n = qe.getInfo().length();
        //System.out.println(s.substring(n-phrase.length(), n));
        if(where.equals("end"))
        {       
            return s.substring(n-phrase.length(), n).equals(phrase);
        }
        else if(where.equals("start"))
        {
                return s.substring(0, phrase.length()).equals(phrase);
        }
        else if(where.equals("any"))
        {
            for(int i = 0; i < n-phrase.length(); i++)
                {
                    if(s.substring(i, i+phrase.length()).equals(phrase))
                        return true;
                }
        }
        return false;
    }
}

