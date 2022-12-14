package checker.VisitorStuff;

import checker.Clase.*;
import checker.Clase.Error;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;

public final class HomePageNAuVisitor implements Visitor {

    @Override
    public void visit(final Page page, final DataBase dataBase,
                      final Action action, final ArrayNode output, final Error error) {
        if (action.getType().equals("change page")) {
                if (action.getPage().equals("login")) {
                    page.setCurrentPage("login");
                } else if (action.getPage().equals("register")) {
                    page.setCurrentPage("register");
                } else {
                    //Error error = new Error();
                    output.addPOJO(error);
                }

        } else if (action.getType().equals("on page")) {
            if ((page.getCurrentPage().equals("login")) && (action.getFeature().equals("login"))) {
                int ok = 0;
                for (User user: dataBase.getUsers()) {
                    if ((user.getCredentials().getName().equals(action
                            .getCredentials().getName()))
                            && (user.getCredentials().getPassword().equals(action
                                    .getCredentials().getPassword()))) {
                        ok = 1;
                        User aux = new User(user);
                        dataBase.setCurrentUser(new User());
                        dataBase.getCurrentUser().setCredentials(new Credentials());
                        dataBase.getCurrentUser().getCredentials()
                                .setName(aux.getCredentials().getName());
                        dataBase.getCurrentUser().getCredentials()
                                .setPassword(aux.getCredentials().getPassword());
                        dataBase.getCurrentUser().getCredentials()
                                .setBalance(aux.getCredentials().getBalance());
                        dataBase.getCurrentUser().getCredentials()
                                .setCountry(aux.getCredentials().getCountry());
                        dataBase.getCurrentUser().getCredentials()
                                .setAccountType(aux.getCredentials().getAccountType());
                        for (Movie movie: aux.getPurchasedMovies()) {
                            dataBase.getCurrentUser().getPurchasedMovies().add(movie);
                        }
                        for (Movie movie: aux.getLikedMovies()) {
                            dataBase.getCurrentUser().getLikedMovies().add(movie);
                        }
                        dataBase.getCurrentUser()
                                .setNumFreePremiumMovies(aux.getNumFreePremiumMovies());
                        for (Movie movie: aux.getWatchedMovies()) {
                            dataBase.getCurrentUser().getWatchedMovies().add(movie);
                        }
                        for (Movie movie: aux.getRatedMovies()) {
                            dataBase.getCurrentUser().getRatedMovies().add(movie);
                        }

                        dataBase.getCurrentUser().setTokensCount(aux.getTokensCount());
                        ArrayList<Movie> currentList = new ArrayList<>();
                        page.setCurrentPage("HomeA");
                        output.addObject().putPOJO("error", null)
                                .putPOJO("currentMoviesList", currentList)
                                .putPOJO("currentUser", aux);
                    }
                }
                if (ok == 0) {
                    //Error error = new Error();
                    output.addPOJO(error);
                }
            } else if ((page.getCurrentPage().equals("register"))
                    && (action.getFeature().equals("register"))) {
                page.setCurrentPage("HomeA");
                User user = new User();
                Credentials credentials = new Credentials();
                credentials.setAccountType(action.getCredentials().getAccountType());
                credentials.setBalance(action.getCredentials().getBalance());
                credentials.setCountry(action.getCredentials().getCountry());
                credentials.setPassword(action.getCredentials().getPassword());
                credentials.setName(action.getCredentials().getName());
                user.setCredentials(credentials);
                user.setLikedMovies(new ArrayList<>());
                user.setPurchasedMovies(new ArrayList<>());
                user.setTokensCount(0);
                user.setNumFreePremiumMovies(15);
                user.setWatchedMovies(new ArrayList<>());
                user.setRatedMovies(new ArrayList<>());

                dataBase.getUsers().add(user);
                dataBase.setCurrentUser(new User());
                dataBase.getCurrentUser().setCredentials(new Credentials());
                dataBase.getCurrentUser().getCredentials()
                        .setName(user.getCredentials().getName());
                dataBase.getCurrentUser().getCredentials()
                        .setPassword(user.getCredentials().getPassword());
                dataBase.getCurrentUser().getCredentials()
                        .setBalance(user.getCredentials().getBalance());
                dataBase.getCurrentUser().getCredentials()
                        .setCountry(user.getCredentials().getCountry());
                dataBase.getCurrentUser().getCredentials()
                        .setAccountType(user.getCredentials().getAccountType());
                for (Movie movie: user.getPurchasedMovies()) {
                    dataBase.getCurrentUser().getPurchasedMovies().add(movie);
                }
                for (Movie movie: user.getLikedMovies()) {
                    dataBase.getCurrentUser().getLikedMovies().add(movie);
                }
                dataBase.getCurrentUser().setNumFreePremiumMovies(user.getNumFreePremiumMovies());
                for (Movie movie: user.getWatchedMovies()) {
                    dataBase.getCurrentUser().getWatchedMovies().add(movie);
                }
                for (Movie movie: user.getRatedMovies()) {
                    dataBase.getCurrentUser().getRatedMovies().add(movie);
                }

                dataBase.getCurrentUser().setTokensCount(user.getTokensCount());
                ArrayList<Movie> array = new ArrayList<>();
                output.addObject().putPOJO("error", null)
                        .putPOJO("currentMoviesList", array)
                        .putPOJO("currentUser", user);


            }

        }
    }
}
