public class GenreFilter implements Filter {
    private String genre; 

    public GenreFilter(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean satisfies(String id) {
        // TODO Auto-generated method stub
        if (MovieDatabase.getGenres(id).contains(genre)) {
            return true;
        }
        return false;
    }

}
