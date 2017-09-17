
public class DirectorsFilter implements Filter{

    private String directors;
    
    public DirectorsFilter(String a)
    {
        directors = a;
    }
    
    @Override
    public boolean satisfies(String id)
    {
        String d[] = MovieDatabase.getDirector(id).split(", ");
        String chk[] = directors.split(",");
        for(int i = 0; i < chk.length; i++)
        {
            for(int j = 0 ; j < d.length; j++)
            {
                if(chk[i].equals(d[j])) return true;
            }
        }
        return false;
    }
}
