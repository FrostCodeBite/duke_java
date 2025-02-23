package FilteringData;

import java.util.ArrayList;

public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filterArrayList;

    public MatchAllFilter() {
        this.filterArrayList = new ArrayList<Filter>();
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        // TODO Auto-generated method stub
        for (Filter f: filterArrayList) {
            if (!f.satisfies(qe)) {
                return false;
            }
        }

        return true;
    }

    public void addFilter(Filter filter) {
        filterArrayList.add(filter);
    }

    

}
