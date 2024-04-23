package com.example.service;

import com.example.model.Movie;
import com.example.model.User;
import com.example.util.MovieFilter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieService {
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Movie> movies = new ArrayList<>();

    static {
        try {
            movies = loadMoviesFromJson("../java/com/example/data/movies.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Movie> loadMoviesFromJson(String filePath) throws IOException, JSONException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONArray jsonArray = new JSONArray(jsonContent);

        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String title = jsonObject.getString("title");
            List<String> actors = getListFromJSONArray(jsonObject.getJSONArray("actors"));
            String genre = jsonObject.getString("genre");
            LocalDate releaseDate = LocalDate.parse(jsonObject.getString("releaseDate"));
            int budget = jsonObject.getInt("budget");

            Movie movie = new Movie(title, actors, genre, releaseDate, budget);
            movies.add(movie);
        }
        return movies;
    }

    private static List<String> getListFromJSONArray(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }

    public void searchMovies() {
        System.out.println("Search movies by:");
        System.out.println("1. Title");
        System.out.println("2. Cast");
        System.out.println("3. Category");
        System.out.println("4. All");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        List<Movie> filteredMovies;
        switch (choice) {
            case 1:
                System.out.print("Enter the movie title (or part of it): ");
                String title = scanner.nextLine();
                filteredMovies = MovieFilter.filterMoviesByTitle(movies, title);
                break;
            case 2:
                System.out.print("Enter the cast member's name (or part of it): ");
                String castMember = scanner.nextLine();
                filteredMovies = MovieFilter.filterMoviesByCast(movies, castMember);
                break;
            case 3:
                System.out.print("Enter the movie category: ");
                String category = scanner.nextLine();
                filteredMovies = MovieFilter.filterMoviesByCategory(movies, category);
                break;
            case 4:
                filteredMovies = movies;
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        MovieFilter.displayMovies(filteredMovies);
    }

    public void viewMovieDetails() {
        System.out.print("Enter movie title to see details: ");
        String title = scanner.nextLine();
        Movie movie = MovieFilter.findMovieByTitle(movies, title);
        if (movie != null) {
            System.out.println(movie);
//            System.out.println("Title: " + movie.getTitle());
//            System.out.println("Cast: " + movie.getCast());
//            System.out.println("Category: " + movie.getCategory());
//            System.out.println("Release Date: " + movie.getReleaseDate());
//            System.out.println("Budget: " + movie.getBudget());
        } else {
            System.out.println("Movie not found.");
        }
    }

    public void addMovieToFavorites(User user) {
        System.out.print("Enter movie title to add to favourites: ");
        String title = scanner.nextLine();
        Movie movie = MovieFilter.findMovieByTitle(movies, title);
        if (movie != null) {
            user.addToFavorites(movie);
            System.out.println("Added movie '" + title + "' to favorites for user: " + user.getEmail());
        } else {
            System.out.println("Movie not found.");
        }
    }

    public void removeMovieFromFavorites(User user) {
        System.out.print("Enter movie title to remove from favourites: ");
        String title = scanner.nextLine();
        Movie movie = MovieFilter.findMovieByTitle(movies, title);
        if (movie != null) {
            user.removeFromFavorites(movie);
            System.out.println("Removed movie '" + title + "' from favorites for user: " + user.getEmail());
        } else {
            System.out.println("Movie not found.");
        }
    }

    public void searchFavoriteMovies(User user) {
        List<Movie> favoriteMovies = user.getFavourites();
        if (!favoriteMovies.isEmpty()) {
            System.out.println("Favorite movies for user: " + user.getEmail());
            favoriteMovies.forEach(movie -> System.out.println("Title: " + movie.getTitle()));
        } else {
            System.out.println("No favorite movies found for user: " + user.getEmail());
        }
    }

    public void viewUserDetails(User user) {
        System.out.println(user);
    }
}
