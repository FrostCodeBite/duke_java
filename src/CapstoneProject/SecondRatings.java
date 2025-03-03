/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

 import java.util.*; 

 public class SecondRatings {
     private ArrayList<Movie> myMovies;
     private ArrayList<EfficientRater> myRaters;
     
     public SecondRatings() {
         // default constructor
         this("data/ratedmoviesfull.csv", "data/ratings.csv");
     }
     
    public SecondRatings(String moviefile, String ratingsfile) {
        //TODO Auto-generated constructor stub
        FirstRatings first = new FirstRatings();
        myMovies = first.loadMovies(moviefile);
        myRaters = first.loadRaters(ratingsfile);
    }

    public int getMovieSize() {
        return myMovies.size();
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

        for (int i = 0; i < myMovies.size(); i++) {
            String movieID = myMovies.get(i).getID();
            double avgRating = getAverageByID(movieID, minimalRaters);
            // System.err.println(myMovies.get(i).getID() +" : "+ratingValue);
            if (avgRating != 0.0) {
                rating.add(new Rating(movieID, avgRating));
            }
        }

        return rating;
    }

    public String getTitle(String movieID) {
        String title = "ID was not found";

        for (int i = 0; i < myMovies.size(); i++) {
            String movie_id = myMovies.get(i).getID();
            if (movie_id.equals(movieID)) {
                return myMovies.get(i).getTitle();
            }
        }

        return title;
    }

    public String getID(String title) {
        String movieId = "NO SUCH TITLE.";

        for (int i = 0; i < myMovies.size(); i++) {
            String mTitle = myMovies.get(i).getTitle();
            if (mTitle.equals(title)) {
                return myMovies.get(i).getID();
            }
        }

        return movieId;
    }
     
 }