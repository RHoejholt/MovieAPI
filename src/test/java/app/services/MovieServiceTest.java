package app.services;

import app.persistence.dtos.MovieDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {

    MovieService ms = new MovieService();

    @Test
    void getMovieById() throws IOException, InterruptedException {
        MovieDTO movie = ms.getMovieById("139");
        assertEquals("Mifune", movie.getTitle());;
    }

    @Test
    void getMoviesByTitle() throws IOException, InterruptedException {
        List<MovieDTO> movies = ms.getMoviesByTitle("Mifune");
        assertFalse(movies.isEmpty(), "No movies found!");
        assertEquals("Mifune", movies.get(0).getTitle());
    }

    @Test
    void getMoviesByOverview() throws IOException, InterruptedException {
        List<MovieDTO> movies = ms.getMoviesByOverview("the");
        assertFalse(movies.isEmpty(), "No movies found!");
        assertTrue(movies.get(0).getOverview().contains("the"));

    }

    @Test
    void getMoviesByOverview2() throws IOException, InterruptedException {
        List<MovieDTO> movies = ms.getMoviesByOverview("svennebanan69");
        assertTrue(movies.isEmpty(), "No movies found!");

    }

        @Test
    void getMoviesByReleaseDate() throws IOException, InterruptedException {
        List<MovieDTO> movies = ms.getMoviesByReleaseDate("1994-09-09");
        assertFalse(movies.isEmpty(), "No movies found!");
        assertEquals(movies.get(0).getRelease_date().toString(), "Fri Sep 09 02:00:00 CEST 1994");
    }
}