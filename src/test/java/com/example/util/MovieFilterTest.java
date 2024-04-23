package com.example.util;

import com.example.model.Movie;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MovieFilterTest {
    @Test
    public void testFilterMoviesByTitle() {
        List<Movie> movies = createMovieList();
        List<Movie> filteredMovies = MovieFilter.filterMoviesByTitle(movies, "Movie 1");
        assertEquals(1, filteredMovies.size());
        assertEquals("Movie 1", filteredMovies.get(0).getTitle());
    }

    @Test
    public void testFilterMoviesByCast() {
        List<Movie> movies = createMovieList();
        List<Movie> filteredMovies = MovieFilter.filterMoviesByCast(movies, "Actor 1");
        assertEquals(1, filteredMovies.size());
        assertEquals("Movie 1", filteredMovies.get(0).getTitle());
    }

    @Test
    public void testFilterMoviesByCategory() {
        List<Movie> movies = createMovieList();
        List<Movie> filteredMovies = MovieFilter.filterMoviesByCategory(movies, "Action");
        assertEquals(1, filteredMovies.size());
        assertEquals("Movie 1", filteredMovies.get(0).getTitle());
    }

    @Test
    public void testFindMovieByTitle() {
        List<Movie> movies = createMovieList();
        Movie foundMovie = MovieFilter.findMovieByTitle(movies, "Movie 2");
        assertEquals("Movie 2", foundMovie.getTitle());
    }

    @Test
    public void testFindMovieByTitleNotFound() {
        List<Movie> movies = createMovieList();
        Movie foundMovie = MovieFilter.findMovieByTitle(movies, "Non-existent Movie");
        assertNull(foundMovie);
    }

    private List<Movie> createMovieList() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Movie 1", List.of("Actor 1", "Actor 2"), "Action", LocalDate.of(2020, 1, 1), 100000000));
        movies.add(new Movie("Movie 2", List.of("Actor 3", "Actor 4"), "Comedy", LocalDate.of(2021, 6, 15), 50000000));
        movies.add(new Movie("Movie 3", List.of("Actor 5", "Actor 6"), "Drama", LocalDate.of(2022, 10, 20), 75000000));
        return movies;
    }
}
