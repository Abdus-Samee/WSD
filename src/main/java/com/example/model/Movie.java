package com.example.model;

import java.time.LocalDate;
import java.util.List;

public class Movie {
    private String title;
    private List<String> cast;
    private String category;
    private LocalDate releaseDate;
    private double budget;

    public Movie(String title, List<String> cast, String category, LocalDate releaseDate, double budget) {
        this.title = title;
        this.cast = cast;
        this.category = category;
        this.releaseDate = releaseDate;
        this.budget = budget;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getCast() {
        return cast;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public double getBudget() {
        return budget;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title).append("\n");
        sb.append("Cast: ").append(cast).append("\n");
        sb.append("Category: ").append(category).append("\n");
        sb.append("Release Date: ").append(releaseDate).append("\n");
        sb.append("Budget: ").append(budget);
        return sb.toString();
    }
}
