package checker.Clase;


import java.util.ArrayList;

public final class User {
    private Credentials credentials;

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public User() {

    }
    private int tokensCount;
    private int numFreePremiumMovies = 15;
    private ArrayList<Movie> purchasedMovies = new ArrayList<>();
    private ArrayList<Movie> watchedMovies = new ArrayList<>();
    private ArrayList<Movie> likedMovies = new ArrayList<>();
    private ArrayList<Movie> ratedMovies = new ArrayList<>();

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public User(final User user) {
        this.credentials = new Credentials(user.getCredentials());
        this.tokensCount = user.getTokensCount();
        this.numFreePremiumMovies = user.getNumFreePremiumMovies();

        ArrayList<Movie> auxPur = new ArrayList<>();
        for (Movie movie: user.getPurchasedMovies()) {
            Movie movie1 = new Movie(movie);
            auxPur.add(movie1);
        }
        this.purchasedMovies = auxPur;

        ArrayList<Movie> auxWatched = new ArrayList<>();
        for (Movie movie: user.getWatchedMovies()) {
            Movie movie1 = new Movie(movie);
            auxWatched.add(movie1);
        }
        this.watchedMovies = auxWatched;

        ArrayList<Movie> auxLiked = new ArrayList<>();
        for (Movie movie: user.getLikedMovies()) {
            Movie movie1 = new Movie(movie);
            auxLiked.add(movie1);
        }
        this.likedMovies = auxLiked;

        ArrayList<Movie> auxRated = new ArrayList<>();
        for (Movie movie: user.getRatedMovies()) {
            Movie movie1 = new Movie(movie);
            auxRated.add(movie1);
        }
        this.ratedMovies = auxRated;
    }
}
