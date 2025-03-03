import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {

    public void printAverageRatings() {
        ThirdRatings third = new ThirdRatings();
        int ratersNum = third.getRaterSize();
        System.err.println("Total Raters Number are: "+ratersNum);

        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.err.println("Total Movie Number are: "+movies.size());
        
        ArrayList<Rating> rating = third.getAverageRatings(35);
        for (int i = 0; i < rating.size(); i++) {
            String movieID = rating.get(i).getItem();
            String title = MovieDatabase.getTitle(movieID);
            double score = rating.get(i).getValue();
            System.err.println(score+" "+title);
        }
        Collections.sort(rating, Collections.reverseOrder());
        System.err.println("Found "+rating.size());
    }

    public void printAverageRatingsByYearAfter() {
        ThirdRatings third = new ThirdRatings();
        ArrayList<Rating> rating = third.getAverageRatingsByFilter(20 , new YearAfterFilter(2000));
        Collections.sort(rating, Collections.reverseOrder());
        for (int i = 0; i < rating.size(); i++) {
            String movieID = rating.get(i).getItem();
            String title = MovieDatabase.getTitle(movieID);
            double score = rating.get(i).getValue();
            System.err.println(score+" "+title);
        }
        System.err.println("Found "+rating.size());
    }

    public void printAverageRatingsByGenre() {
        ThirdRatings third = new ThirdRatings();
        ArrayList<Rating> rating = third.getAverageRatingsByFilter(20, new GenreFilter("Comedy"));
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

    public void printAverageRatingsByMinutes() {
        ThirdRatings third = new ThirdRatings();
        ArrayList<Rating> rating = third.getAverageRatingsByFilter(5, new MinutesFilter(105, 135));
        Collections.sort(rating, Collections.reverseOrder());
        for (int i = 0; i < rating.size(); i++) {
            String movieID = rating.get(i).getItem();
            String title = MovieDatabase.getTitle(movieID);
            double score = rating.get(i).getValue();
            int minutes = MovieDatabase.getMinutes(movieID);
            System.err.println(score+" "+title+" with time: "+minutes);
        }
        System.err.println("Found "+rating.size());
    }

    public void printAverageRatingsByDirectors() {
        ThirdRatings third = new ThirdRatings();
        ArrayList<Rating> rating = third.getAverageRatingsByFilter(4, new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        Collections.sort(rating, Collections.reverseOrder());
        for (int i = 0; i < rating.size(); i++) {
            String movieID = rating.get(i).getItem();
            String title = MovieDatabase.getTitle(movieID);
            double score = rating.get(i).getValue();
            System.err.println(score+" "+title);
            String direcotr = MovieDatabase.getDirector(movieID);
            System.err.println("    "+direcotr);
        }
        System.err.println("Found "+rating.size());
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings third = new ThirdRatings();
        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(1990));
        af.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> rating = third.getAverageRatingsByFilter(8, af);
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

    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings third = new ThirdRatings();
        AllFilters af = new AllFilters();
        af.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        af.addFilter(new MinutesFilter(90, 180));
        ArrayList<Rating> rating = third.getAverageRatingsByFilter(3, af);
        Collections.sort(rating, Collections.reverseOrder());
        for (int i = 0; i < rating.size(); i++) {
            String movieID = rating.get(i).getItem();
            String title = MovieDatabase.getTitle(movieID);
            double score = rating.get(i).getValue();
            System.err.println(score+" "+title);
            String diector = MovieDatabase.getDirector(movieID);
            System.err.println("    "+diector);
        }
        System.err.println("Found "+rating.size());
    }

    public static void main(String[] args) {
        MovieRunnerWithFilters obj = new MovieRunnerWithFilters();
        // obj.printAverageRatings();
        // obj.printAverageRatingsByYearAfter();
        // obj.printAverageRatingsByGenre();
        // obj.printAverageRatingsByMinutes();
        // obj.printAverageRatingsByDirectors();
        // obj.printAverageRatingsByYearAfterAndGenre();
        obj.printAverageRatingsByDirectorsAndMinutes();
    }
}
