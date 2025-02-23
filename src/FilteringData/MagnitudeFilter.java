package FilteringData;

public class MagnitudeFilter implements Filter {

    private double minMagnitude; 
    private double maxMagnitude;
    
    public MagnitudeFilter(double minMagnitude, double maxMagnitude) {
        this.minMagnitude = minMagnitude;
        this.maxMagnitude = maxMagnitude;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        // TODO Auto-generated method stub
        return minMagnitude <= qe.getMagnitude() && qe.getMagnitude() <= maxMagnitude;
    }

    

}
