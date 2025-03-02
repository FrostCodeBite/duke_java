import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {

    public void getAverageRatingOneMovie() {
        SecondRatings second = new SecondRatings();
        String movieTitle = "Moneyball";
        String movieId = second.getID(movieTitle);
        ArrayList<Rating> rating = second.getAverageRatings(3);
        for (int i = 0; i < rating.size(); i++) {
            if (rating.get(i).getItem().equals(movieId)) {
                System.err.println(movieTitle+" "+rating.get(i).getValue());
            }
        }
    }

    public void printAverageRatings() {
        SecondRatings second = new SecondRatings();
        int movieNum = second.getMovieSize();
        int ratersNum = second.getRaterSize();
        System.err.println("Number of Movies: "+movieNum+", Number of Ratings: "+ratersNum);

        int minRater = 12;
        ArrayList<Rating> rating = second.getAverageRatings(minRater);
        Collections.sort(rating, Collections.reverseOrder());
        for (int i = 0; i < rating.size(); i++) {
            String title = second.getTitle(rating.get(i).getItem());
            double avgRating = rating.get(i).getValue();
            System.err.println(avgRating+" "+title);
        }
        System.err.println("Total Movies with Min Raters of "+minRater+" are: "+rating.size());
    }

    public static void main(String[] args) {
        MovieRunnerAverage obj = new MovieRunnerAverage();
        // obj.printAverageRatings();
        obj.getAverageRatingOneMovie();


    }
}
