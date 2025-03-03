import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movies = new ArrayList<Movie>();

        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record: parser) {
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String genre = record.get("genre");
            String director = record.get("director");
            String country = record.get("country");
            String poster = record.get("poster");
            int minutes = Integer.parseInt(record.get("minutes"));
            movies.add(new Movie(id, title, year, genre, director, country, poster, minutes));
        }
        return movies;
    }

    public void testLoadMovies() {
        int comedyMovies = 0; 
        int greater150Min = 0;
        HashMap<String, Integer> directMovieHM = new HashMap<String, Integer>();
        int maxDirectorValue = 0;
        String maxDirectorName = "";

        String fileName = "data/ratedmoviesfull.csv";
        ArrayList<Movie> moviesList = loadMovies(fileName);
        System.err.println("Total Number of Movies: "+moviesList.size());
        // for (int i = 0 ; i < moviesList.size(); i++) {
        //     System.err.println("Movie "+i+" : "+moviesList.get(i).toString());
        // }

        for (int i = 0 ; i < moviesList.size(); i++) {
            String comedy = moviesList.get(i).getGenres();
            int minutes = moviesList.get(i).getMinutes();
            if (comedy.contains("Comedy")) {
                comedyMovies++;
            }
            if (minutes > 150) {
                greater150Min++;
            }

            String director = moviesList.get(i).getDirector();
            if (directMovieHM.size() == 0) {
                directMovieHM.put(director, 1);
            } else {
                if (!directMovieHM.containsKey(director)) {
                    directMovieHM.put(director, 1);
                } else {
                    int freq = directMovieHM.get(director);
                    directMovieHM.put(director, freq += 1);
                }
            }
        }
        System.err.println("Total Number of Comedy Movies: "+ comedyMovies);
        System.err.println("Total Number of Movies Greater Than 150 Min: "+ greater150Min);
        for (String director: directMovieHM.keySet()) {
            int freq = directMovieHM.get(director);
            if (freq > maxDirectorValue) {
                maxDirectorValue = freq;
                maxDirectorName = director;
            }
        }
        System.err.println("Maximum number of movies by any director is: "+maxDirectorValue);
        System.err.println("There are "+ maxDirectorName +" directors that directed "+maxDirectorValue+" such movie");
    }

    public ArrayList<EfficientRater> loadRaters(String filename) {
        ArrayList<EfficientRater> raterList = new ArrayList<EfficientRater>();

        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record: parser) {
            String rater_id = record.get("rater_id");
            String movie_id = record.get("movie_id");
            double ratingScore = Double.parseDouble(record.get("rating"));

            EfficientRater rater = new EfficientRater(rater_id);
            rater.addRating(movie_id, ratingScore);
            
            if (!hasRater(raterList, rater_id)) {
                raterList.add(rater);
            } else {
                for (int i = 0 ; i < raterList.size(); i++) {
                    EfficientRater r = raterList.get(i); 
                    if (r.getID().equals(rater_id)) {
                        r.addRating(movie_id, ratingScore);
                        raterList.set(i, r);
                    }
                }
            }

        }
        return raterList;
    }

    public boolean hasRater(ArrayList<EfficientRater> raterList, String rater_id) {
        boolean exist = false;
        // IF RATER EXIST IN ARRAYLIST RETURN TRUE;
        for (EfficientRater rater: raterList) {
            String raterID = rater.getID();
            if (raterID.equals(rater_id)) {
                return true;
            }
        }
        return exist;
    }

    public void testLoadRaters() {
        int totalRaterofMovie = 0;
        int maxRatingIndex = 0;
        int maxRatingSize = 0;
        ArrayList<String> uniqueMovie = new ArrayList<String>();

        String fileName = "data/ratings.csv";
        ArrayList<EfficientRater> raterList = loadRaters(fileName);
        System.err.println("Total Number of Raters: "+raterList.size());

        String raterID = "193";
        String movieID = "1798709";
        for (int i = 0; i < raterList.size(); i++) {
            int ratingSize = raterList.get(i).getItemsRated().size();
            for (int j = 0; j < ratingSize; j++) {
                String movie_id = raterList.get(i).getItemsRated().get(j);
                if (movie_id.equals(movieID)) {
                    totalRaterofMovie++;
                    // System.err.println("Rating of Movie "+movie_id+" is: "+raterList.get(i).getRating(movie_id)+" which rate by: "+raterList.get(i).getID());
                }
                if (uniqueMovie.size() == 0) {
                    uniqueMovie.add(movie_id);
                } else {
                    if (!uniqueMovie.contains(movie_id)) {
                        uniqueMovie.add(movie_id);
                    }
                }
            } 
            if (maxRatingSize < ratingSize) {
                maxRatingSize = ratingSize;
                maxRatingIndex = i;
            }
            if (raterList.get(i).getID().equals(raterID)) {
                System.err.println("Total Rating of Rater "+ raterID+ " is: "+ratingSize);
            }
        }
        System.err.println("Total Number of Raters for Movie "+movieID+" is: "+totalRaterofMovie);
        System.err.println("Rater with Most Number of Rated Movie is: "+raterList.get(maxRatingIndex).getID()+", with total rating of: "+maxRatingSize);
        System.err.println("Total unique movie with rating are: "+uniqueMovie.size());
    }

    public static void main(String[] args) {
        FirstRatings obj = new FirstRatings();
        // obj.testLoadMovies();
        obj.testLoadRaters();
    }
}
