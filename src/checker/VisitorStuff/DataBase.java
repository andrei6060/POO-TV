package checker.VisitorStuff;

import checker.Clase.Input;
import checker.Clase.Movie;
import checker.Clase.User;

import java.util.ArrayList;

public class DataBase {
    User currentUser = null;
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Movie> movies = new ArrayList<>();
    ArrayList<Movie> moviesForUser = new ArrayList<>();
    ArrayList<Movie> moviesForSee = new ArrayList<>();

    public ArrayList<Movie> getMoviesForSee() {
        return moviesForSee;
    }

    public void setMoviesForSee(ArrayList<Movie> moviesForSee) {
        this.moviesForSee = moviesForSee;
    }

    public DataBase(Input input){
        users = new ArrayList<>(input.getUsers());

        movies = new ArrayList<>();
        for(Movie movie: input.getMovies()){
            Movie movie1 = new Movie(movie);
            movies.add(movie1);
        }

    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ArrayList<Movie> getMoviesForUser() {
        return moviesForUser;
    }

    public void setMoviesForUser(ArrayList<Movie> moviesForUser) {
        this.moviesForUser = moviesForUser;
    }
}
