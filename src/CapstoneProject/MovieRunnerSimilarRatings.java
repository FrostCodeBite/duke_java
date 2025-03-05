import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {

    public void printAverageRatings() {
        FourthRatings four = new FourthRatings();
        int ratersNum = four.getRaterSize();
        System.err.println("Total Raters Number are: "+ratersNum);

        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.err.println("Total Movie Number are: "+movies.size());
        
        ArrayList<Rating> rating = four.getAverageRatings(35);
        for (int i = 0; i < rating.size(); i++) {
            String movieID = rating.get(i).getItem();
            String title = MovieDatabase.getTitle(movieID);
            double score = rating.get(i).getValue();
            System.err.println(score+" "+title);
        }
        Collections.sort(rating, Collections.reverseOrder());
        System.err.println("Found "+rating.size());
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        FourthRatings four = new FourthRatings();
        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(1990));
        af.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> rating = four.getAverageRatingsByFilter(8, af);
        Collections.sort(rating, Collections.reverseOrder());
        for (int i = 0; i < rating.size(); i++) {
            String movieID = rating.get(i).getItem();
            String title = MovieDatabase.getTitle(movieID);
            double score = rating.get(i).getValue();
            System.err.println(score+" "+title);
            String genre = MovieDatabase.getGenres(movieID);
            System.err.println("    "+genre);
        }
        System.err.println("Found "+rating.size());
    }

    public void printSimilarRatings() {
        FourthRatings four = new FourthRatings();
        ArrayList<Rating> rating = four.getSimilarRatings("71", 20, 5);
        String movieTitle = MovieDatabase.getTitle(rating.get(0).getItem());
        double weight = rating.get(0).getValue();
        System.err.println("Movie with the top rated average is: "+ movieTitle+ ", with weight: "+weight);
        
    }

    public void printSimilarRatingsByGenre() {
        FourthRatings four = new FourthRatings();
        ArrayList<Rating> rating = four.getSimilarRatingsByFilter("964", 20, 5, new GenreFilter("Mystery"));
        String movieTitle = MovieDatabase.getTitle(rating.get(0).getItem());
        double weight = rating.get(0).getValue();
        System.err.println("Movie with the top rated average is: "+ movieTitle+ ", with weight: "+weight);
    }

    public void printSimilarRatingsByDirector() {
        FourthRatings four = new FourthRatings();
        ArrayList<Rating> rating = four.getSimilarRatingsByFilter("120", 10, 2, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        String movieTitle = MovieDatabase.getTitle(rating.get(0).getItem());
        double weight = rating.get(0).getValue();
        System.err.println("Movie with the top rated average is: "+ movieTitle+ ", with weight: "+weight);
    }

    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings four = new FourthRatings();
        AllFilters af = new AllFilters();
        af.addFilter(new GenreFilter("Drama"));
        af.addFilter(new MinutesFilter(80, 160));
        ArrayList<Rating> rating = four.getSimilarRatingsByFilter("168", 10, 3, af);
        String movieTitle = MovieDatabase.getTitle(rating.get(0).getItem());
        double weight = rating.get(0).getValue();
        System.err.println("Movie with the top rated average is: "+ movieTitle+ ", with weight: "+weight);
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings four = new FourthRatings();
        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(1975));
        af.addFilter(new MinutesFilter(70, 200));
        ArrayList<Rating> rating = four.getSimilarRatingsByFilter("314", 10, 5, af);
        String movieTitle = MovieDatabase.getTitle(rating.get(0).getItem());
        double weight = rating.get(0).getValue();
        System.err.println("Movie with the top rated average is: "+ movieTitle+ ", with weight: "+weight);
    }
    

    public static void main(String[] args) {
        MovieRunnerSimilarRatings obj = new MovieRunnerSimilarRatings();
        // obj.printAverageRatings();
        // obj.printAverageRatingsByYearAfterAndGenre();
        // obj.printSimilarRatings();
        // obj.printSimilarRatingsByGenre();
        // obj.printSimilarRatingsByDirector();
        // obj.printSimilarRatingsByGenreAndMinutes();
        obj.printSimilarRatingsByYearAfterAndMinutes();

    }
}
