package checker.Clase;

import java.util.ArrayList;

public class Error {
    public String error;
    public ArrayList<Movie> currentMoviesList;
    public User currentUser;

    private static Error instance = null;

    private Error() {
        this.error = "Error";
        this.currentMoviesList = new ArrayList<>();
        this.currentUser = null;

    }

    public static Error getInstance() {
        if (instance == null) {
            instance = new Error();
        }
        return instance;
    }


}
