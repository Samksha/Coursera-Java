
public class MinutesFilter implements Filter{
    
    private int min, max;
    
    public MinutesFilter(int a, int b)
    {
        min = a;
        max = b;
    }

    @Override
    public boolean satisfies(String id) {
	    return (MovieDatabase.getMinutes(id) >= min && MovieDatabase.getMinutes(id) <= max);
	}
}
