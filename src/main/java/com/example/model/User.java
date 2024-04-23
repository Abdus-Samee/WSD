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
}
