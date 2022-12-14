package checker.VisitorStuff;

import checker.Clase.Input;
import checker.Clase.Movie;
import checker.Clase.User;

import java.util.ArrayList;

public final class DataBase {
    private User currentUser = null;
    private ArrayList<User> users;
    private ArrayList<Movie> movies;
    private ArrayList<Movie> moviesForUser = new ArrayList<>();
    private ArrayList<Movie> moviesForSee = new ArrayList<>();

    public ArrayList<Movie> getMoviesForSee() {
        return moviesForSee;
    }

    public void setMoviesForSee(final ArrayList<Movie> moviesForSee) {
        this.moviesForSee = moviesForSee;
    }

    public DataBase(final Input input) {
        users = new ArrayList<>(input.getUsers());

        movies = new ArrayList<>();
        for (Movie movie: input.getMovies()) {
            Movie movie1 = new Movie(movie);
            movies.add(movie1);
        }

    }
//    private static DataBase instance = null;
//
//
//    public static DataBase getDatabase(final Input input) {
//        if (instance == null) {
//            instance = new DataBase(input);
//        }
//        return instance;
//    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ArrayList<Movie> getMoviesForUser() {
        return moviesForUser;
    }

    public void setMoviesForUser(final ArrayList<Movie> moviesForUser) {
        this.moviesForUser = moviesForUser;
    }
}
