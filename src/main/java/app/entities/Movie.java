package app.entities;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Movie {
    private String title;
    private double vote_average;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public double getVoteAverage() { return vote_average; }
    public void setVoteAverage(double vote_average) { this.vote_average = vote_average; }

    @Override
    public String toString() {
        return "Movie{title='" + title + "', rating=" + vote_average + "}";
    }
}
