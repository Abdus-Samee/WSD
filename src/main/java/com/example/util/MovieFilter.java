package com.example.util;

import com.example.model.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieFilter {
    public static List<Movie> filterMoviesByTitle(List<Movie> movies, String title) {
        // Implement movie filtering by title here
        return movies.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static List<Movie> filterMoviesByCast(List<Movie> movies, String castMember) {
        // Implement movie filtering by cast member here
        return movies.stream()
                .filter(movie -> movie.getCast().contains(castMember))
                .collect(Collectors.toList());
    }

    public static List<Movie> filterMoviesByCategory(List<Movie> movies, String category) {
        // Implement movie filtering by category here
        return movies.stream()
                .filter(movie -> movie.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public static Movie findMovieByTitle(List<Movie> movies, String title) {
        return movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public static void displayMovies(List<Movie> movies) {
        if (movies.isEmpty()) {
            System.out.println("No movies found.");
        } else {
            System.out.println("Search results:");
            for (Movie movie : movies) {
                System.out.println("- " + movie.getTitle());
            }
        }
    }
}
