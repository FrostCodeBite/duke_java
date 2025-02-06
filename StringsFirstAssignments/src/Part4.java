import java.util.ArrayList;

import edu.duke.URLResource;

public class Part4 {

    public static ArrayList<String> findURLs(String URL) {

        ArrayList<String> arrayList = new ArrayList<>();
    
            URLResource urls = new URLResource(URL);
    
            for (String currURL : urls.words()) {

                if (currURL.toUpperCase().contains("YOUTUBE.COM")) {
                    
                    int start = currURL.toUpperCase().indexOf("HREF=\"");
                    int end = currURL.toUpperCase().lastIndexOf("\"");

                    String url = currURL.substring(start+6, end);

                    arrayList.add(url);
                }
            } 

            return arrayList;
        
        }
    
        public static void main(String[] args) {
            String URL = "https://www.dukelearntoprogram.com/course2/data/manylinks.html";
            ArrayList<String> list = findURLs(URL);

            System.err.println(list);
    }
}
