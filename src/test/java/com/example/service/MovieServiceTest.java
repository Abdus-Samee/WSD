package com.example.service;

import com.example.model.Movie;
import com.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieServiceTest {
    private MovieService movieService;
    private User mockedUser;
    private List<Movie> mockedMovies;

    @BeforeEach
    void setUp() {
        mockedUser = new User("test@gmail.com");
        mockedMovies = createMockMovies();
        movieService = new MovieService(mockedMovies);
    }

    @Test
    public void testAddMovieToFavorites() {
        Movie movieToAdd = mockedMovies.get(0);
        movieService.addMovieToFavourites(mockedUser, movieToAdd.getTitle());

        assertEquals(1, mockedUser.getFavourites().size());
        assertEquals(movieToAdd, mockedUser.getFavourites().get(0));
    }

    @Test
    public void testAddMovieToFavoritesNull() {
        Movie movieToAdd = null;
        movieService.addMovieToFavourites(mockedUser, null);

        assertEquals(0, mockedUser.getFavourites().size());
    }

    @Test
    public void testRemoveMovieFromFavorites() {
        Movie movieToRemove = mockedMovies.get(0);
        movieService.removeMovieFromFavourites(mockedUser, movieToRemove.getTitle());

        assertEquals(0, mockedUser.getFavourites().size());
    }

    @Test
    public void testRemoveMovieFromFavoritesNull() {
        List<Movie> initialFavorites = mockedUser.getFavourites();
        movieService.removeMovieFromFavourites(mockedUser, null);

        assertEquals(0, mockedUser.getFavourites().size());
    }

    @Test
    public void testViewMovieDetails() {
        Movie movieToView = mockedMovies.get(3);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        movieService.viewMovieDetails(movieToView.getTitle());
        System.setOut(originalOut);

        String expected = "\n"+
                          "Title: " + movieToView.getTitle() + "\n" +
                          "Cast: " + movieToView.getCast() + "\n" +
                          "Category: " + movieToView.getCategory() + "\n" +
                          "Release Date: " + movieToView.getReleaseDate() + "\n" +
                          "Budget: " + movieToView.getBudget() + "\n";

        assertEquals(expected.trim(), outputStream.toString().trim());
    }

    private List<Movie> createMockMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Movie 1", List.of("Actor 7", "Actor 8"), "Thriller", LocalDate.of(2023, 5, 10), 90000000));
        movies.add(new Movie("Movie 2", List.of("Actor 1", "Actor 2"), "Comedy", LocalDate.of(2020, 1, 28), 12000000));
        movies.add(new Movie("Movie 3", List.of("Actor 4", "Actor 6", "Actor 9"), "Romance", LocalDate.of(1997, 11, 17), 12500000));
        movies.add(new Movie("Movie 4", List.of("Actor 3", "Actor 5"), "Slice of Life", LocalDate.of(2002, 12, 07), 41000000));
        return movies;
    }
}
