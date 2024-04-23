package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private List<Movie> favourites;

    public User(String email){
        this.email = email;
        this.favourites = new ArrayList<Movie>();
    }

    public String getEmail(){
        return email;
    }

    public List<Movie> getFavourites() {
        return favourites;
    }

    public void addToFavorites(Movie movie) {
        favourites.add(movie);
    }

    public void removeFromFavorites(Movie movie) {
        favourites.remove(movie);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User Email: ").append(email).append("\n");
        if (favourites != null && !favourites.isEmpty()) {
            sb.append("Favorite Movies:\n");
            for (Movie movie : favourites) {
                sb.append("Title: ").append(movie.getTitle()).append("\n");
                sb.append("Cast: ").append(movie.getCast()).append("\n");
                sb.append("Category: ").append(movie.getCategory()).append("\n");
                sb.append("Release Date: ").append(movie.getReleaseDate()).append("\n");
                sb.append("Budget: ").append(movie.getBudget()).append("\n\n");
            }
        } else {
            sb.append("No favorite movies found.\n");
        }
        return sb.toString();
    }
}
