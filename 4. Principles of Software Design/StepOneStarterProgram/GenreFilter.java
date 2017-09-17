
public class GenreFilter implements Filter {
    
    private String genre;
    
    public GenreFilter(String gen)
    {
        genre = gen;
    }

    @Override
	public boolean satisfies(String id) {
	    String g[] = MovieDatabase.getGenres(id).split(", ");
            for(int j = 0; j < g.length; j++)
            {
                if(g[j].equals(genre)) return true;
            }
		return false;
	}
}
