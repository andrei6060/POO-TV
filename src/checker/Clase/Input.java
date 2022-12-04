package checker.Clase;

import java.util.ArrayList;

public class Input {
    ArrayList <User> users = new ArrayList<>();
    ArrayList <Movie> movies = new ArrayList<>();

    ArrayList <Action> actions = new ArrayList<>();

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
