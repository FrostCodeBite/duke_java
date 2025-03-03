public class DirectorsFilter implements Filter{
    private String director;

    public DirectorsFilter(String director) {
        this.director = director;
    }

    @Override
    public boolean satisfies(String id) {
        // TODO Auto-generated method stub
        String[] directorList = director.split(",");
        for(int i = 0; i < directorList.length; i++) {
            if (MovieDatabase.getDirector(id).contains(directorList[i])) {
                return true;
           }
        }
       
        return false;
    }

}
