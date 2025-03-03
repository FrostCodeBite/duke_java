import java.util.ArrayList;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;

    public ThirdRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public ThirdRatings(String moviefile, String ratingsfile) {
        //TODO Auto-generated constructor stub
        FirstRatings first = new FirstRatings();
        myRaters = first.loadRaters("data/"+ratingsfile);
        MovieDatabase.initialize(moviefile);
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    private double getAverageByID(String movidID, int minimalRaters) {
        double avgRating = 0.0;
        double totalRating = 0.0;
        int countRater = 0;

        for(int i = 0; i < myRaters.size(); i++) {
            if (myRaters.get(i).getItemsRated().contains(movidID)) {
                countRater++;
                totalRating += myRaters.get(i).getRating(movidID);
            }
        }
        if (countRater >= minimalRaters) {
            avgRating = totalRating/countRater;
        }
        
        return avgRating;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> rating = new ArrayList<>();

        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (int i = 0; i < movies.size(); i++) {
            String movieID = movies.get(i);
            double avgRating = getAverageByID(movieID, minimalRaters);
            if (avgRating != 0.0) {
                rating.add(new Rating(movieID, avgRating));
            }
        }

        return rating;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> rating = new ArrayList<Rating>();
        
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (int i = 0; i < movies.size(); i++) {
            String movieID = movies.get(i);
            double avgRating = getAverageByID(movieID, minimalRaters);
            if (avgRating != 0.0) {
                rating.add(new Rating(movieID, avgRating));
            }
        }
        return rating;
    }
}
