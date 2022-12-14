package checker.Clase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
@JsonIgnoreProperties({ "ratings" })
public final class Movie {
    private String name;
    private Integer year;
    private Integer duration;
    private ArrayList<Integer> ratings = new ArrayList<>();
    private ArrayList<String> genres = new ArrayList<>();
    private ArrayList<String> actors = new ArrayList<>();
    private ArrayList<String> countriesBanned = new ArrayList<>();
    private int numLikes;
    private int numRatings;
    private double rating;
    public  Movie() {

    }

    public Movie(final Movie movie) {
        this.setNumRatings(movie.getNumRatings());
        this.setRating(movie.getRating());
        this.setNumLikes(movie.getNumLikes());
        this.setName(movie.getName());
        this.setYear(movie.getYear());
        this.setDuration(movie.getDuration());
        for (String string:movie.getCountriesBanned()) {
            this.getCountriesBanned().add(string);
        }
        for (String string:movie.getActors()) {
            this.getActors().add(string);
        }
        for (String string:movie.getGenres()) {
            this.getGenres().add(string);
        }
        for (Integer intt:movie.getRatings()) {
            this.getRatings().add(intt);
        }
    }

    public ArrayList<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(final ArrayList<Integer> ratings) {
        this.ratings = ratings;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(final Integer year) {
        this.year = year;
    }

    public Integer getDuration() {
        return duration;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(final double rating) {
        this.rating = rating;
    }

    public void setDuration(final Integer duration) {
        this.duration = duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }
}
