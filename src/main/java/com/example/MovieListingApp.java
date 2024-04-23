package com.example;

import com.example.model.Movie;
import com.example.model.User;
import com.example.service.MovieService;
import com.example.service.RegistrationService;
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

public class MovieListingApp {
    private static final RegistrationService registrationService = new RegistrationService();
    private static MovieService movieService;
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Movie> movies;

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

    public static void main(String[] args) {
        try {
            movies = loadMoviesFromJson("src/main/java/com/example/data/movies.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        movieService = new MovieService(movies);

        boolean exit = false;

        while(!exit){
            System.out.println("Welcome to the Movie Listing App!");
            System.out.println("1. Register");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = 0;

            try{
                choice = scanner.nextInt();
                scanner.nextLine();
            }catch (Exception e){
                choice = -1;
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    exit = true;
                    System.out.println("Exiting the application...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();

        boolean isRegistered = registrationService.registerUser(email);
        while(!isRegistered){
            System.out.println("Registration failed. Please try again with a valid email address.");
            System.out.print("Enter your email address: ");
            email = scanner.nextLine();
            isRegistered = registrationService.registerUser(email);
        }

        System.out.println("Registration successful!");
        User user = registrationService.getUser(email);
        handleUserMenu(user);
    }

    private static void handleUserMenu(User user) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nUser Menu");
            System.out.println("1. Search Movies");
            System.out.println("2. View Movie Details");
            System.out.println("3. Add Movie to Favorites");
            System.out.println("4. Remove Movie from Favorites");
            System.out.println("5. View User Details");
            System.out.println("6. Search Favorite Movies");
            System.out.println("7. Goto Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    movieService.searchMovies();
                    break;
                case 2:
                    System.out.print("Enter movie title to see details: ");
                    String title2 = scanner.nextLine();
                    movieService.viewMovieDetails(title2);
                    break;
                case 3:
                    System.out.print("Enter movie title to add to favourites: ");
                    String title3 = scanner.nextLine();
                    movieService.addMovieToFavourites(user, title3);
                    break;
                case 4:
                    System.out.print("Enter movie title to remove from favourites: ");
                    String title4 = scanner.nextLine();
                    movieService.removeMovieFromFavourites(user, title4);
                    break;
                case 5:
                    movieService.viewUserDetails(user);
                    break;
                case 6:
                    movieService.searchFavouriteMovies(user);
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
