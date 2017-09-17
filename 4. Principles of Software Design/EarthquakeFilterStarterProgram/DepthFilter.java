
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter
{
    private double maxDepth;
    private double minDepth;
    
    public DepthFilter(double a, double b)
    {
        minDepth = a;
        maxDepth = b;
    }
    
    public boolean satisfies(QuakeEntry qe)
    {
        return (qe.getDepth()>=minDepth && qe.getDepth()<=maxDepth);
    }
}
