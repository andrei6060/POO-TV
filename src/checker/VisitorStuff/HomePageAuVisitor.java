package checker.VisitorStuff;

import checker.Clase.Action;
import checker.Clase.Error;
import checker.Clase.Movie;
import checker.Clase.User;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;

public class HomePageAuVisitor implements Visitor {

    @Override
    public void visit(Page page, DataBase dataBase, Action action, ArrayNode output) {
        if (action.getType().equals("change page")) {
            if (page.currentPage.equals("HomeA")) {
                if (action.getPage().equals("logout")) {
                    dataBase.setCurrentUser(null);
                    page.currentPage = "HomeUnA";
                }
                if ((action.getPage().equals("login")) || (action.getPage().equals("register"))) {
                    Error error = new Error();
                    output.addPOJO(error);
                }
                if (action.getPage().equals("movies")) {
                    page.currentPage = "movies";
                        dataBase.setMoviesForUser(new ArrayList<>());
                        int k;
                        for (Movie movie : dataBase.getMovies()) {
                            k = 0;
                            for (String name : movie.getCountriesBanned())
                                if (dataBase.currentUser.getCredentials().getCountry().equals(name)) {
                                    k++;
                                }
                            if (k == 0)
                                dataBase.getMoviesForUser().add(movie);
                        }
                    ArrayList<Movie> currentMoviesList = new ArrayList<>();
                    for (Movie movie:dataBase.getMoviesForUser()) {
                        Movie movie1 = new Movie(movie);
                        currentMoviesList.add(movie1);
                    }

                    ArrayList<Movie> newPur = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getPurchasedMovies()){
                        Movie movie1 = new Movie(movie);
                        newPur.add(movie1);
                    }
                    ArrayList<Movie> newW = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getWatchedMovies()){
                        Movie movie1 = new Movie(movie);
                        newW.add(movie1);
                    }
                    ArrayList<Movie> newL = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getLikedMovies()){
                        Movie movie1 = new Movie(movie);
                        newL.add(movie1);
                    }
                    int newNum = dataBase.currentUser.getNumFreePremiumMovies();
                    ArrayList<Movie> newR = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getRatedMovies()){
                        Movie movie1 = new Movie(movie);
                        newR.add(movie1);
                    }

                    User incercare = new User(dataBase.currentUser);
                    incercare.setWatchedMovies(newW);
                    incercare.setPurchasedMovies(newPur);
                    incercare.setRatedMovies(newR);
                    incercare.setLikedMovies(newL);
                    incercare.setNumFreePremiumMovies(newNum);
                    output.addObject().putPOJO("error", null).putPOJO("currentMoviesList", currentMoviesList).putPOJO("currentUser", incercare);
                } else if (action.getPage().equals("upgrades")) {
                    page.currentPage = "upgrades";

                }
            } else if (page.currentPage.equals("movies")) {
                if(action.getPage().equals("see details")){
                    Movie movie1 = new Movie();
                    int ok =0;
                    for(Movie movie : dataBase.getMoviesForUser()){
                        if(movie.getName().equals(action.getMovie())){
                            movie1.setActors(movie.getActors());
                            movie1.setName(movie.getName());
                            movie1.setDuration(movie.getDuration());
                            movie1.setGenres(movie.getGenres());
                            movie1.setRating(movie.getRating());
                            movie1.setCountriesBanned(movie.getCountriesBanned());
                            movie1.setYear(movie.getYear());
                            movie1.setNumRatings(movie.getNumRatings());
                            int a = movie.getNumLikes();
                            movie1.setNumLikes(a);
                            ok = 1;
                        }
                    }
                    if(ok == 1) {
                        page.currentPage = "see details";
                        dataBase.getMoviesForSee().add(movie1);
                        ArrayList<Movie> auxx = new ArrayList<>();
                        for(Movie movie: dataBase.getMoviesForSee()){
                            Movie movie2 = new Movie(movie);
                            auxx.add(movie2);
                        }

                        User aux = new User(dataBase.currentUser);
                        User plm = new User(aux);
                        plm.getCredentials().setAccountType(dataBase.currentUser.getCredentials().getAccountType());
                        plm.getCredentials().setName(dataBase.currentUser.getCredentials().getName());
                        plm.getCredentials().setPassword(dataBase.currentUser.getCredentials().getPassword());
                        plm.getCredentials().setCountry(dataBase.currentUser.getCredentials().getCountry());
                        plm.getCredentials().setBalance(dataBase.currentUser.getCredentials().getBalance());
                        plm.setWatchedMovies(new ArrayList<>());
                        plm.setPurchasedMovies(new ArrayList<>());
                        plm.setRatedMovies(new ArrayList<>());
                        plm.setLikedMovies(new ArrayList<>());

                        for(Movie movie: dataBase.getCurrentUser().getWatchedMovies()){
                            Movie movie2 = new Movie(movie);
                            plm.getWatchedMovies().add(movie2);
                        }
                        for(Movie movie: dataBase.getCurrentUser().getPurchasedMovies()){
                            Movie movie2 = new Movie(movie);
                            plm.getPurchasedMovies().add(movie2);
                        }
                        for(Movie movie:  dataBase.getCurrentUser().getRatedMovies()){
                            Movie movie2 = new Movie(movie);
                            plm.getRatedMovies().add(movie2);
                        }
                        for(Movie movie:  dataBase.getCurrentUser().getLikedMovies()){
                            Movie movie2 = new Movie(movie);
                            plm.getLikedMovies().add(movie2);
                        }
                        if(auxx.size()>1) {
                            for (int i = 0; i < auxx.size() - 1; i++)
                                auxx.remove(auxx.get(i));
                        }
                        Movie movieaux = new Movie(auxx.get(auxx.size()-1));
                        auxx = new ArrayList<>();
                        auxx.add(movieaux);

                        output.addObject().putPOJO("error", null).putPOJO("currentMoviesList", auxx).putPOJO("currentUser", plm);
                    }
                    else {
                        Error error = new Error();
                        output.addPOJO(error);
                    }
                } else if(action.getPage().equals("logout")){
                    dataBase.setCurrentUser(null);
                    page.currentPage = "HomeUnA";
                } else if (action.getPage().equals("login")) {
                    Error error = new Error();
                    output.addPOJO(error);

                } else if(action.getPage().equals("movies")){
                    page.currentPage = "movies";
                    dataBase.setMoviesForUser(new ArrayList<>());
                    int k;
                    for (Movie movie : dataBase.getMovies()) {
                        k = 0;
                        for (String name : movie.getCountriesBanned())
                            if (dataBase.currentUser.getCredentials().getCountry().equals(name)) {
                                k++;
                            }
                        if (k == 0)
                            dataBase.getMoviesForUser().add(movie);
                    }
                    ArrayList<Movie> currentMoviesList = new ArrayList<>();
                    for (Movie movie:dataBase.getMoviesForUser()) {
                        Movie movie1 = new Movie(movie);
                        currentMoviesList.add(movie1);
                    }

                    ArrayList<Movie> newPur = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getPurchasedMovies()){
                        Movie movie1 = new Movie(movie);
                        newPur.add(movie1);
                    }
                    ArrayList<Movie> newW = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getWatchedMovies()){
                        Movie movie1 = new Movie(movie);
                        newW.add(movie1);
                    }
                    ArrayList<Movie> newL = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getLikedMovies()){
                        Movie movie1 = new Movie(movie);
                        newL.add(movie1);
                    }
                    int newNum = dataBase.currentUser.getNumFreePremiumMovies();
                    ArrayList<Movie> newR = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getRatedMovies()){
                        Movie movie1 = new Movie(movie);
                        newR.add(movie1);
                    }

                    User incercare = new User(dataBase.currentUser);
                    incercare.setWatchedMovies(newW);
                    incercare.setPurchasedMovies(newPur);
                    incercare.setRatedMovies(newR);
                    incercare.setLikedMovies(newL);
                    incercare.setNumFreePremiumMovies(newNum);
                    output.addObject().putPOJO("error", null).putPOJO("currentMoviesList", currentMoviesList).putPOJO("currentUser", incercare);

                }

            } else if(page.currentPage.equals("upgrades")){
                if(action.getPage().equals("movies")){
                    page.currentPage = "movies";
                    dataBase.setMoviesForUser(new ArrayList<>());
                    int k;
                    for (Movie movie : dataBase.getMovies()) {
                        k = 0;
                        for (String name : movie.getCountriesBanned())
                            if (dataBase.currentUser.getCredentials().getCountry().equals(name)) {
                                k++;
                            }
                        if (k == 0)
                            dataBase.getMoviesForUser().add(movie);
                    }
                    //}
                    ArrayList<Movie> currentMoviesList = new ArrayList<>();
                    for (Movie movie:dataBase.getMoviesForUser()) {
                        Movie movie1 = new Movie(movie);
                        currentMoviesList.add(movie1);
                    }

                    ArrayList<Movie> newPur = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getPurchasedMovies()){
                        Movie movie1 = new Movie(movie);
                        newPur.add(movie1);
                    }
                    ArrayList<Movie> newW = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getWatchedMovies()){
                        Movie movie1 = new Movie(movie);
                        newW.add(movie1);
                    }
                    ArrayList<Movie> newL = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getLikedMovies()){
                        Movie movie1 = new Movie(movie);
                        newL.add(movie1);
                    }
                    int newNum = dataBase.currentUser.getNumFreePremiumMovies();
                    ArrayList<Movie> newR = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getRatedMovies()){
                        Movie movie1 = new Movie(movie);
                        newR.add(movie1);
                    }

                    User incercare = new User(dataBase.currentUser);
                    incercare.setWatchedMovies(newW);
                    incercare.setPurchasedMovies(newPur);
                    incercare.setRatedMovies(newR);
                    incercare.setLikedMovies(newL);
                    incercare.setNumFreePremiumMovies(newNum);
                    output.addObject().putPOJO("error", null).putPOJO("currentMoviesList", currentMoviesList).putPOJO("currentUser", incercare);

                }
            }else if(page.currentPage.equals("see details")){
                if(action.getPage().equals("movies")){
                    //page.currentPage = "movies";
                    page.currentPage = "movies";
                    if((dataBase.getMoviesForUser().size() == 0) || (dataBase.getMoviesForUser().size() == 1)) {
                    dataBase.setMoviesForUser(new ArrayList<>());
                    int k;
                    for (Movie movie : dataBase.getMovies()) {
                        k = 0;
                        for (String name : movie.getCountriesBanned())
                            if (dataBase.currentUser.getCredentials().getCountry().equals(name)) {
                                k++;
                            }
                        if (k == 0)
                            dataBase.getMoviesForUser().add(movie);
                    }
                    }

                    ArrayList<Movie> currentMoviesList = new ArrayList<>();
                    for(Movie movie:dataBase.getMoviesForUser()){
                        Movie movie1 = new Movie(movie);
                        currentMoviesList.add(movie1);
                    }
                    for(Movie movie :currentMoviesList) {
                        for(Movie movie1: dataBase.getMoviesForSee())
                            if(movie.getName().equals(movie1.getName()))
                                movie.setNumLikes(movie1.getNumLikes());

                    }



                    ArrayList<Movie> newPur = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getPurchasedMovies()){
                        Movie movie1 = new Movie(movie);
                        newPur.add(movie1);
                    }
                    ArrayList<Movie> newW = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getWatchedMovies()){
                        Movie movie1 = new Movie(movie);
                        newW.add(movie1);
                    }
                    ArrayList<Movie> newL = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getLikedMovies()){
                        Movie movie1 = new Movie(movie);
                        newL.add(movie1);
                    }
                    int newNum = dataBase.currentUser.getNumFreePremiumMovies();
                    ArrayList<Movie> newR = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getRatedMovies()){
                        Movie movie1 = new Movie(movie);
                        newR.add(movie1);
                    }

                    User incercare = new User(dataBase.currentUser);
                    incercare.setWatchedMovies(newW);
                    incercare.setPurchasedMovies(newPur);
                    incercare.setRatedMovies(newR);
                    incercare.setLikedMovies(newL);
                    incercare.setNumFreePremiumMovies(newNum);


                    output.addObject().putPOJO("error", null).putPOJO("currentMoviesList", currentMoviesList).putPOJO("currentUser", incercare);

                } else if (action.getPage().equals("logout")) {
                    dataBase.setCurrentUser(null);
                    dataBase.setMoviesForSee(new ArrayList<>());
                    page.currentPage = "HomeUnA";

                } else if (action.getPage().equals("see details")) {
                    Error error = new Error();
                    output.addPOJO(error);
                }
            }

        } else if (action.getType().equals("on page")) {
            if (page.currentPage.equals("HomeA")) {
                if ((action.getFeature().equals("login")) || (action.getFeature().equals("register"))) {
                    Error error = new Error();
                    output.addPOJO(error);
                }
                if(action.getFeature().equals("filter")){
                    Error error = new Error();
                    output.addPOJO(error);
                }
            } else if (page.currentPage.equals("movies")) {
                if (action.getFeature().equals("search")) {

                    ArrayList<Movie> MoviesList = new ArrayList<>();
                    for (Movie movie : dataBase.getMoviesForUser()) {

                        int k = 0;
                        for (int i = 0; i < action.getStartsWith().length(); i++) {
                            if (movie.getName().length() >= action.getStartsWith().length()) {
                                if (movie.getName().charAt(i) != action.getStartsWith().charAt(i))
                                    k++;
                            } else {
                                k = 1;
                            }
                        }
                        if (k == 0) {
                            MoviesList.add(movie);
                        }

                    }
                    User plm = new User(dataBase.currentUser);
                    plm.getCredentials().setAccountType(dataBase.currentUser.getCredentials().getAccountType());
                    plm.getCredentials().setName(dataBase.currentUser.getCredentials().getName());
                    plm.getCredentials().setPassword(dataBase.currentUser.getCredentials().getPassword());
                    plm.getCredentials().setCountry(dataBase.currentUser.getCredentials().getCountry());
                    plm.getCredentials().setBalance(dataBase.currentUser.getCredentials().getBalance());
                    output.addObject().putPOJO("error", null).putPOJO("currentMoviesList", MoviesList).putPOJO("currentUser", plm);

                }
                if (action.getFeature().equals("filter")) {
                    ArrayList<Movie> newArr = new ArrayList<>();
                    int k;
                    for (Movie movie : dataBase.getMovies()) {
                        k = 0;
                        for (String name : movie.getCountriesBanned())
                            if (dataBase.currentUser.getCredentials().getCountry().equals(name)) {
                                k++;
                            }
                        if (k == 0)
                            newArr.add(movie);
                    }
                    if(action.getFilters().getSort() != null){
                    if (action.getFilters().getSort().getRating() != null) {

                        if(action.getFilters().getSort().getRating().equals("decreasing")){
                            newArr.sort((o1, o2) -> (int) (o2.getRating() - o1.getRating()));
                        }else{
                            newArr.sort((o1, o2) -> (int) (o1.getRating() - o2.getRating()));
                        }
                    }
                    if(action.getFilters().getSort().getDuration() != null){
                        if(action.getFilters().getSort().getDuration().equals("decreasing")){
                            newArr.sort((o1, o2) -> o2.getDuration() - o1.getDuration());
                        }else{
                            newArr.sort((o1, o2) -> o1.getDuration() - o2.getDuration());
                        }
                    }}
                    if(action.getFilters().getContains() != null){
                        if(action.getFilters().getContains().getActors() != null){
                            newArr.removeIf(movie -> !(movie.getActors().contains(action.getFilters().getContains().getActors().get(0))));
                        }
                        if(action.getFilters().getContains().getGenre() != null){
                            newArr.removeIf(movie -> !(movie.getGenres().contains(action.getFilters().getContains().getGenre().get(0))));
                        }
                    }

                    ArrayList<Movie> newPur = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getPurchasedMovies()){
                        Movie movie1 = new Movie(movie);
                        newPur.add(movie1);
                    }
                    ArrayList<Movie> newW = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getWatchedMovies()){
                        Movie movie1 = new Movie(movie);
                        newW.add(movie1);
                    }
                    ArrayList<Movie> newL = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getLikedMovies()){
                        Movie movie1 = new Movie(movie);
                        newL.add(movie1);
                    }
                    int newNum = dataBase.currentUser.getNumFreePremiumMovies();
                    ArrayList<Movie> newR = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getRatedMovies()){
                        Movie movie1 = new Movie(movie);
                        newR.add(movie1);
                    }
                    dataBase.setMoviesForUser(new ArrayList<>());
                    for(Movie movie:newArr){
                        Movie movie1 = new Movie(movie);
                        dataBase.getMoviesForUser().add(movie1);
                    }

                    User incercare = new User(dataBase.currentUser);
                    incercare.setWatchedMovies(newW);
                    incercare.setPurchasedMovies(newPur);
                    incercare.setRatedMovies(newR);
                    incercare.setLikedMovies(newL);
                    incercare.setNumFreePremiumMovies(newNum);

                    output.addObject().putPOJO("error", null).putPOJO("currentMoviesList", newArr).putPOJO("currentUser", incercare);
                } else if ((action.getFeature().equals("purchase")) || (action.getFeature().equals("rate")) || (action.getFeature().equals("watch")) || (action.getFeature().equals("like"))) {
                    Error error = new Error();
                    output.addPOJO(error);

                }
            } else if (page.currentPage.equals("upgrades")) {
                if(action.getFeature().equals("buy tokens")){
                    dataBase.currentUser.setTokensCount(dataBase.currentUser.getTokensCount() + action.getCount());
                    dataBase.currentUser.getCredentials().setBalance(Integer.toString(Integer.parseInt(dataBase.currentUser.getCredentials().getBalance()) - action.getCount()));

                } else if (action.getFeature().equals("buy premium account")) {
                    dataBase.currentUser.setTokensCount(dataBase.currentUser.getTokensCount() - 10);
                    dataBase.currentUser.getCredentials().setAccountType("premium");

                    ArrayList<Movie> newPur = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getPurchasedMovies()){
                        Movie movie1 = new Movie(movie);
                        newPur.add(movie1);
                    }
                    ArrayList<Movie> newW = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getWatchedMovies()){
                        Movie movie1 = new Movie(movie);
                        newW.add(movie1);
                    }
                    ArrayList<Movie> newL = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getLikedMovies()){
                        Movie movie1 = new Movie(movie);
                        newL.add(movie1);
                    }
                    int newNum = dataBase.currentUser.getNumFreePremiumMovies();
                    ArrayList<Movie> newR = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getRatedMovies()){
                        Movie movie1 = new Movie(movie);
                        newR.add(movie1);
                    }

                    User incercare = new User(dataBase.currentUser);
                    incercare.setWatchedMovies(newW);
                    incercare.setPurchasedMovies(newPur);
                    incercare.setRatedMovies(newR);
                    incercare.setLikedMovies(newL);
                    incercare.setNumFreePremiumMovies(newNum);
                    ArrayList<Movie> auxx = new ArrayList<>();
                    if(dataBase.getMoviesForUser().size()==0){
                        int k;
                        for (Movie movie : dataBase.getMovies()) {
                            k = 0;
                            for (String name : movie.getCountriesBanned())
                                if (dataBase.currentUser.getCredentials().getCountry().equals(name)) {
                                    k++;
                                }
                            if (k == 0)
                                dataBase.getMoviesForUser().add(movie);
                        }

                    }
                    for(Movie movie:dataBase.getMoviesForUser()){
                        Movie movie1 = new Movie(movie);
                        auxx.add(movie1);
                    }

                }

            } else if (page.currentPage.equals("see details")) {
                if(action.getFeature().equals("purchase")){
                    dataBase.currentUser.getPurchasedMovies().add(dataBase.getMoviesForSee().get(dataBase.getMoviesForSee().size() -1));
                    if((dataBase.currentUser.getNumFreePremiumMovies() > 0) &&(dataBase.currentUser.getCredentials().getAccountType().equals("premium"))) {
                        dataBase.currentUser.setNumFreePremiumMovies(dataBase.currentUser.getNumFreePremiumMovies() - 1);
                    }
                    else{
                        dataBase.currentUser.setTokensCount(dataBase.currentUser.getTokensCount() - 2);
                    }

                    ArrayList<Movie> newPur = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getPurchasedMovies()){
                        Movie movie1 = new Movie(movie);
                        newPur.add(movie1);
                    }
                    ArrayList<Movie> newW = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getWatchedMovies()){
                        Movie movie1 = new Movie(movie);
                        newW.add(movie1);
                    }
                    ArrayList<Movie> newL = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getLikedMovies()){
                        Movie movie1 = new Movie(movie);
                        newL.add(movie1);
                    }
                    int newNum = dataBase.currentUser.getNumFreePremiumMovies();
                    ArrayList<Movie> newR = new ArrayList<>();
                    for(Movie movie: dataBase.currentUser.getRatedMovies()){
                        Movie movie1 = new Movie(movie);
                        newR.add(movie1);
                    }

                    User incercare = new User(dataBase.currentUser);
                    incercare.setWatchedMovies(newW);
                    incercare.setPurchasedMovies(newPur);
                    incercare.setRatedMovies(newR);
                    incercare.setLikedMovies(newL);
                    incercare.setNumFreePremiumMovies(newNum);
                    ArrayList<Movie> aux = new ArrayList<>();
                    for(Movie movie : dataBase.getMoviesForSee()){
                        Movie movie1 = new Movie(movie);
                        aux.add(movie1);
                    }
                    if(aux.size()>1){
                        for(int  i = 0; i< aux.size()-1; i++){
                            aux.remove(aux.get(i));
                        }
                    }
                    Movie movieaux = new Movie(aux.get(aux.size()-1));
                    aux = new ArrayList<>();
                    aux.add(movieaux);
                    output.addObject().putPOJO("error", null).putPOJO("currentMoviesList", aux).putPOJO("currentUser", incercare);


                    
                } else if (action.getFeature().equals("watch")) {
                    String nume;
                    if(action.getMovie() == null)
                        nume = dataBase.getMoviesForSee().get(dataBase.getMoviesForSee().size() - 1).getName();
                    else
                        nume = action.getMovie();
                    int k = 0;
                        for (Movie movie : dataBase.getCurrentUser().getPurchasedMovies()) {
                            if (movie.getName().equals(nume)) {
                                k++;
                            }
                        }
                        if (k == 0) {
                            Error error = new Error();
                            output.addPOJO(error);
                        }else{

                        dataBase.currentUser.getWatchedMovies().add(dataBase.getMoviesForSee().get(dataBase.getMoviesForSee().size() -1));
                        ArrayList<Movie> newPur = new ArrayList<>();
                        for (Movie movie : dataBase.currentUser.getPurchasedMovies()) {
                            Movie movie1 = new Movie(movie);
                            newPur.add(movie1);
                        }
                        ArrayList<Movie> newW = new ArrayList<>();
                        for (Movie movie : dataBase.currentUser.getWatchedMovies()) {
                            Movie movie1 = new Movie(movie);
                            newW.add(movie1);
                        }
                        ArrayList<Movie> newL = new ArrayList<>();
                        for (Movie movie : dataBase.currentUser.getLikedMovies()) {
                            Movie movie1 = new Movie(movie);
                            newL.add(movie1);
                        }
                        int newNum = dataBase.currentUser.getNumFreePremiumMovies();
                        ArrayList<Movie> newR = new ArrayList<>();
                        for (Movie movie : dataBase.currentUser.getRatedMovies()) {
                            Movie movie1 = new Movie(movie);
                            newR.add(movie1);
                        }

                        User incercare = new User(dataBase.currentUser);
                        incercare.setWatchedMovies(newW);
                        incercare.setPurchasedMovies(newPur);
                        incercare.setRatedMovies(newR);
                        incercare.setLikedMovies(newL);
                        incercare.setNumFreePremiumMovies(newNum);
                        ArrayList<Movie> aux = new ArrayList<>();
                        for (Movie movie : dataBase.getMoviesForSee()) {
                            Movie movie1 = new Movie(movie);
                            aux.add(movie1);

                        }
                        if(aux.size()>1) {
                            for (int i = 0; i < aux.size() - 1; i++)
                                aux.remove(aux.get(i));
                        }
                            Movie movieaux = new Movie(aux.get(aux.size()-1));
                            aux = new ArrayList<>();
                            aux.add(movieaux);
                        output.addObject().putPOJO("error", null).putPOJO("currentMoviesList", aux).putPOJO("currentUser", incercare);
                    }

                } else if (action.getFeature().equals("like")) {
                    String nume;
                    if(action.getMovie() == null)
                        nume = dataBase.getMoviesForSee().get(dataBase.getMoviesForSee().size() - 1).getName();
                    else
                        nume = action.getMovie();
                    int k = 0;
                    for (Movie movie : dataBase.getCurrentUser().getWatchedMovies()) {
                        if (movie.getName().equals(nume)) {
                            k++;
                        }
                    }
                    if (k == 0) {
                        Error error = new Error();
                        output.addPOJO(error);
                    } else{

                        for (Movie movie : dataBase.getMoviesForSee()) {
                            if (movie.getName().equals(nume)) {
                                movie.setNumLikes(movie.getNumLikes() + 1);
                                dataBase.currentUser.getLikedMovies().add(movie);
                            }
                        }

                        ArrayList<Movie> newPur = new ArrayList<>();
                        for (Movie movie : dataBase.currentUser.getPurchasedMovies()) {
                            Movie movie1 = new Movie(movie);
                            newPur.add(movie1);
                        }
                        ArrayList<Movie> newW = new ArrayList<>();
                        for (Movie movie : dataBase.currentUser.getWatchedMovies()) {
                            Movie movie1 = new Movie(movie);
                            newW.add(movie1);
                        }
                        ArrayList<Movie> newL = new ArrayList<>();
                        for (Movie movie : dataBase.currentUser.getLikedMovies()) {
                            Movie movie1 = new Movie(movie);
                            newL.add(movie1);
                        }
                        int newNum = dataBase.currentUser.getNumFreePremiumMovies();
                        ArrayList<Movie> newR = new ArrayList<>();
                        for (Movie movie : dataBase.currentUser.getRatedMovies()) {
                            Movie movie1 = new Movie(movie);
                            newR.add(movie1);
                        }

                    User incercare = new User(dataBase.currentUser);
                    incercare.setWatchedMovies(newW);
                    incercare.setPurchasedMovies(newPur);
                    incercare.setRatedMovies(newR);
                    incercare.setLikedMovies(newL);
                    incercare.setNumFreePremiumMovies(newNum);
                    ArrayList<Movie> aux = new ArrayList<>(dataBase.getMoviesForSee());
                    output.addObject().putPOJO("error", null).putPOJO("currentMoviesList", aux).putPOJO("currentUser", incercare);
                }
                    
                } else if (action.getFeature().equals("rate")) {
                    if(action.getRate() > 5){
                        Error error = new Error();
                        output.addPOJO(error);
                    } else {

                    String nume;
                    if (action.getMovie() == null)
                        nume = dataBase.getMoviesForSee().get(dataBase.getMoviesForSee().size() - 1).getName();
                    else
                        nume = action.getMovie();
                    int k = 0;
                    for (Movie movie : dataBase.getCurrentUser().getWatchedMovies()) {
                        if (movie.getName().equals(nume)) {
                            k++;
                        }
                    }
                    if (k == 0) {
                        Error error = new Error();
                        output.addPOJO(error);
                    } else {
                        double rating = 0;
                        double noRatings = 0;
                        for (Movie movie : dataBase.getMoviesForSee()) {
                            if (movie.getName().equals(nume)) {
                                movie.setNumRatings(movie.getNumRatings() + 1);
                                noRatings = movie.getNumRatings();
                                movie.getRatings().add(action.getRate());
                                rating = 0;
                                for (Integer intt : movie.getRatings())
                                    rating = rating + intt;
                                movie.setRating(rating);
                                dataBase.currentUser.getRatedMovies().add(movie);
                            }
                        }
                        for (Movie movie : dataBase.getMoviesForUser()) {
                            if (movie.getName().equals(nume)) {
                                movie.setRating(rating);
                                movie.setNumRatings((int) noRatings);
                            }
                        }

                        ArrayList<Movie> newPur = new ArrayList<>();
                        for (Movie movie : dataBase.currentUser.getPurchasedMovies()) {
                            Movie movie1 = new Movie(movie);
                            newPur.add(movie1);
                        }
                        ArrayList<Movie> newW = new ArrayList<>();
                        for (Movie movie : dataBase.currentUser.getWatchedMovies()) {
                            Movie movie1 = new Movie(movie);
                            newW.add(movie1);
                        }
                        ArrayList<Movie> newL = new ArrayList<>();
                        for (Movie movie : dataBase.currentUser.getLikedMovies()) {
                            Movie movie1 = new Movie(movie);
                            newL.add(movie1);
                        }
                        int newNum = dataBase.currentUser.getNumFreePremiumMovies();
                        ArrayList<Movie> newR = new ArrayList<>();
                        for (Movie movie : dataBase.currentUser.getRatedMovies()) {
                            Movie movie1 = new Movie(movie);
                            newR.add(movie1);
                        }

                        User incercare = new User(dataBase.currentUser);
                        incercare.setWatchedMovies(newW);
                        incercare.setPurchasedMovies(newPur);
                        incercare.setRatedMovies(newR);
                        incercare.setLikedMovies(newL);
                        incercare.setNumFreePremiumMovies(newNum);
                        ArrayList<Movie> aux = new ArrayList<>();
                        for (Movie movie : dataBase.getMoviesForSee()) {
                            Movie movie1 = new Movie(movie);
                            aux.add(movie1);
                        }
                        if (aux.size() > 1) {
                            for (int i = 0; i < aux.size() - 1; i++)
                                aux.remove(aux.get(i));
                        }
                        Movie movieaux = new Movie(aux.get(aux.size() - 1));
                        aux = new ArrayList<>();
                        aux.add(movieaux);
                        output.addObject().putPOJO("error", null).putPOJO("currentMoviesList", aux).putPOJO("currentUser", incercare);
                    }
                }
                    
                }

            }
        }
    }
}
