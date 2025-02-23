package FilteringData;

public class DepthFilter implements Filter{
    private double minDept;
    private double maxDepth;

    public DepthFilter(double minDept, double maxDepth) {
        this.minDept = minDept;
        this.maxDepth = maxDepth;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        // TODO Auto-generated method stub
        return minDept <= qe.getDepth() && qe.getDepth() <= maxDepth;
    }

    

    
}
