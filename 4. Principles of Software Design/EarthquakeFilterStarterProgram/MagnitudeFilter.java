
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter
{
    private double min;
    private double max;
    
    public MagnitudeFilter(double a, double b)
    {
        min = a;
        max =  b;
    }

    public boolean satisfies(QuakeEntry qe)
    {
        return (qe.getMagnitude()>=min && qe.getMagnitude()<=max);
    }
}
