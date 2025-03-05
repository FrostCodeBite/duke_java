import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater{
    private String myID;
    HashMap<String,Rating> movieRatingHM; 

    public EfficientRater(String id) {
        myID = id;
        movieRatingHM = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {
        Rating rate = new Rating(item, rating);
        movieRatingHM.put(item, rate);
    }

    public boolean hasRating(String item) {        
        return movieRatingHM.containsKey(item);
    }

    public String getID() {
        return myID;
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for (String movieID : movieRatingHM.keySet()) {
            list.add(movieID);
        }
        return list; 
    }

    public double getRating(String item) {
        if (movieRatingHM.containsKey(item)) {
            return movieRatingHM.get(item).getValue();
        }
        
        return -1;
    }

    @Override
    public int numRatings() {
        // TODO Auto-generated method stub
        return movieRatingHM.values().size();
    }
}
