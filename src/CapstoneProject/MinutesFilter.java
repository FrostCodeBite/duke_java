public class MinutesFilter implements Filter{
    private int minMinute;
    private int maxMinute;

    public MinutesFilter(int minMinutes, int maxMinutes) {
        this.minMinute = minMinutes;
        this.maxMinute = maxMinutes;
    }

    @Override
    public boolean satisfies(String id) {
        // TODO Auto-generated method stub
        int minutes = MovieDatabase.getMinutes(id);
        if (minMinute <= minutes && minutes <= maxMinute) {
            return true;
        }
        return false;
    }

}
