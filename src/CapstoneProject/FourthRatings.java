import java.util.ArrayList;
import java.util.Collections;

public class FourthRatings {

    public FourthRatings() {
        // default constructor
        // this("ratedmovies_short.csv", "ratings_short.csv");
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public FourthRatings(String moviefile, String ratingsfile) {
        //TODO Auto-generated constructor stub
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);
    }

    public int getRaterSize() {
        return RaterDatabase.size();
    }

    private double getAverageByID(String movidID, int minimalRaters) {
        double avgRating = 0.0;
        double totalRating = 0.0;
        int countRater = 0;

        for(int i = 0; i < RaterDatabase.size(); i++) {
            ArrayList<String> movieList = RaterDatabase.getRaters().get(i).getItemsRated();
            if (movieList.contains(movidID)) {
                countRater++;
                double ratingScore = RaterDatabase.getRaters().get(i).getRating(movidID);
                totalRating += ratingScore;
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

    public double dotProduct(Rater me, Rater r) {
        ArrayList<String> meItemList = me.getItemsRated();
        ArrayList<String> rItemList = r.getItemsRated();
        double productSum = 0.0;

        for (int i = 0; i < meItemList.size(); i++) {
            String meCurrentItem = meItemList.get(i);
            if (rItemList.contains(meCurrentItem)) {
                productSum += (me.getRating(meCurrentItem)-5) * (r.getRating(meCurrentItem)-5);
            }
        }

        return productSum;
    }

    public ArrayList<Rating> getSimilarities(String rateID) {
        ArrayList<Rating> similar = new ArrayList<Rating>();

        Rater me = RaterDatabase.getRater(rateID);
        for (int i = 0; i < RaterDatabase.size(); i++) {
            Rater r = RaterDatabase.getRaters().get(i);
            double product = dotProduct(me, r);
            if (product >= 0 && !me.equals(r)) {
                similar.add(new Rating(r.getID(), product));
            }
        }
        Collections.sort(similar, Collections.reverseOrder());
        return similar;
    }

    public ArrayList<Rating> getSimilarRatings(String raterID, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        // weight = sum(avgRating*similarRating)/total number of ratings;

        ArrayList<Rating> raterSimilar = getSimilarities(raterID); //RaterID with Similarity
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for (int i = 0; i < movies.size(); i++) {
            String movie = movies.get(i);
            double totalWeight = 0.0;
            double weight = 0.0;
            int n = 0;

            for (int j = 0; j < numSimilarRaters; j++) {
                String rater_id = raterSimilar.get(j).getItem();
                double similarScore = raterSimilar.get(j).getValue();
                double ratingScore = 0.0;
                if (RaterDatabase.getRater(rater_id).getRating(movie) != -1) {
                    ratingScore = RaterDatabase.getRater(rater_id).getRating(movie);
                    weight += similarScore * ratingScore;
                    n++;
                }            
            }
            if (n >= minimalRaters) {
                totalWeight = weight/n;
            }
            ratings.add(new Rating(movie, totalWeight));
        }

        Collections.sort(ratings, Collections.reverseOrder());
        return ratings;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String raterID, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        // weight = sum(avgRating*similarRating)/total number of ratings;

        ArrayList<Rating> raterSimilar = getSimilarities(raterID); //RaterID with Similarity
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

        for (int i = 0; i < movies.size(); i++) {
            String movie = movies.get(i);
            double totalWeight = 0.0;
            double weight = 0.0;
            int n = 0;

            for (int j = 0; j < numSimilarRaters; j++) {
                String rater_id = raterSimilar.get(j).getItem();
                double similarScore = raterSimilar.get(j).getValue();
                double ratingScore = 0.0;
                if (RaterDatabase.getRater(rater_id).getRating(movie) != -1) {
                    ratingScore = RaterDatabase.getRater(rater_id).getRating(movie);
                    weight += similarScore * ratingScore;
                    n++;
                }            
            }
            if (n >= minimalRaters) {
                totalWeight = weight/n;
            }
            ratings.add(new Rating(movie, totalWeight));
        }

        Collections.sort(ratings, Collections.reverseOrder());
        return ratings;
    }
}
