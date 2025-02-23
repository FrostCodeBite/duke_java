package FilteringData;

public class DistanceFilter implements Filter {
    private Location location;
    private double maxDistance;
    
    public DistanceFilter(Location location, double maxDistance) {
        this.location = location;
        this.maxDistance = maxDistance;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        // TODO Auto-generated method stub
        return qe.getLocation().distanceTo(location) < maxDistance;
    }

    
}
