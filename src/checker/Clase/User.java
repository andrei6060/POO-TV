package checker.Clase;


import java.util.ArrayList;

public class User {
    Credentials credentials;

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public User(){

    }
    int tokensCount;
    int numFreePremiumMovies = 15;
    ArrayList<Movie> purchasedMovies = new ArrayList<>();
    ArrayList<Movie> watchedMovies = new ArrayList<>();
    ArrayList<Movie> likedMovies = new ArrayList<>();
    ArrayList<Movie> ratedMovies = new ArrayList<>();

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public User(User user) {
        this.credentials = new Credentials(user.getCredentials());
        this.tokensCount = user.getTokensCount();
        this.numFreePremiumMovies = user.getNumFreePremiumMovies();

        //this.purchasedMovies = user.getPurchasedMovies();
        ArrayList<Movie> auxPur = new ArrayList<>();
        for(Movie movie: user.getPurchasedMovies()){
            Movie movie1 = new Movie(movie);
            auxPur.add(movie1);
        }
        this.purchasedMovies = auxPur;

        //this.watchedMovies = user.getWatchedMovies();
        ArrayList<Movie> auxWatched = new ArrayList<>();
        for(Movie movie: user.getWatchedMovies()){
            Movie movie1 = new Movie(movie);
            auxWatched.add(movie1);
        }
        this.watchedMovies = auxWatched;

        //this.likedMovies = user.getLikedMovies();
        ArrayList<Movie> auxLiked = new ArrayList<>();
        for(Movie movie: user.getLikedMovies()){
            Movie movie1 = new Movie(movie);
            auxLiked.add(movie1);
        }
        this.likedMovies = auxLiked;

        //this.ratedMovies = user.getRatedMovies();
        ArrayList<Movie> auxRated = new ArrayList<>();
        for(Movie movie: user.getRatedMovies()){
            Movie movie1 = new Movie(movie);
            auxRated.add(movie1);
        }
        this.ratedMovies = auxRated;
    }
}
