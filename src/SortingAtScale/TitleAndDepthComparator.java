import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        // TODO Auto-generated method stub

        if (q1.getInfo().compareTo(q2.getInfo()) == 0) {
            return Double.compare(q1.getDepth(), q2.getDepth());
        }

        return q1.getInfo().compareTo(q2.getInfo());
    }

}
