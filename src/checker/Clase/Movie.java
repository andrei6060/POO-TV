package checker.Clase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
@JsonIgnoreProperties({ "ratings" })
public class Movie {
    String name;
    Integer year;
    Integer duration;
    ArrayList<Integer> ratings = new ArrayList<>();
    ArrayList<String> genres = new ArrayList<>();
    ArrayList<String> actors = new ArrayList<>();
    ArrayList<String> countriesBanned = new ArrayList<>();
    int numLikes;
    int numRatings;
    double rating;
    public  Movie(){}

    public Movie(Movie movie){
        this.setNumRatings(movie.getNumRatings());
        this.setRating(movie.getRating());
        this.setNumLikes(movie.getNumLikes());
        this.setName(movie.getName());
        this.setYear(movie.getYear());
        this.setDuration(movie.getDuration());
        for(String string:movie.getCountriesBanned()){
            this.getCountriesBanned().add(string);
        }
        for(String string:movie.getActors()){
            this.getActors().add(string);
        }
        for(String string:movie.getGenres()){
            this.getGenres().add(string);
        }
        for(Integer intt:movie.getRatings()){
            this.getRatings().add(intt);
        }
    }

    public ArrayList<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Integer> ratings) {
        this.ratings = ratings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getDuration() {
        return duration;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }
}
