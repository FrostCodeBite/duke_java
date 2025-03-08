import java.util.ArrayList;
import java.util.Random;

public class RecommendationRunner implements Recommender{

    public ArrayList<String> getItemsToRate() {
        // TODO Auto-generated method stub
        ArrayList<String> itemsToRate = new ArrayList<String> ();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
       
        for (int i=0; i < 10; i++) {
            Random rand = new Random();
            int random = rand.nextInt(movies.size());
            if (! itemsToRate.contains(movies.get(random))) {
                itemsToRate.add(movies.get(random));
            }
        }
        
        return itemsToRate;
    }
    
    public void printRecommendationsFor(String webRaterID) {
        // TODO Auto-generated method stub
        FourthRatings fourthRatings = new FourthRatings ();
        
        int numSimilarRaters = 50; // variable
        int minNumOfRatings = 2; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(webRaterID, numSimilarRaters, minNumOfRatings);
        
        if (similarRatings.size() == 0) {
            System.out.println("No matching movies were found");
        } else {
            System.err.println("");
            String header = ("<h1>Recommendated Movies are: </h1><table> <tr> <th>Movie Title</th> <th>Rating Value</th>  <th>Genres</th> <th>Poster</th>  </tr>");
            String body = "";
            for (int i = 0; i < 10; i++ ) {
                Rating rating = similarRatings.get(i);
                body += "<tr> <td>" + MovieDatabase.getTitle(rating.getItem()) + "</td> <td>" 
                + Double.toString(rating.getValue()) + "</td> <td>" + MovieDatabase.getGenres(rating.getItem())
                + "</td> <td><img src=\""+MovieDatabase.getPoster(rating.getItem()) +"\"></td></tr> ";
            }
            System.out.println(header  + body + "</table>");
        }
		
    }

    public static void main(String[] args) {
        RecommendationRunner obj = new RecommendationRunner();
        obj.printRecommendationsFor("123");
    }
}
