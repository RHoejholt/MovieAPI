package app.services;

import app.persistence.dtos.MovieDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {

    private static String apiKey = System.getenv("API_KEY");
    MovieService ms = new MovieService();

    @Test
    void getMovieById() throws IOException, InterruptedException {
        assertEquals("Mifune", ms.getMovieById("139"));;
    }

    @Test
    void getMoviesByTitle() throws IOException, InterruptedException {
        List<MovieDTO> movies = ms.getMoviesByTitle("Mifune");
        assertFalse(movies.isEmpty(), "No movies found!");
        assertEquals("Mifune", movies.get(0).getTitle());
    }

    @Test
    void getMoviesByOverview() throws IOException, InterruptedException {
        List<MovieDTO> movies = ms.getMoviesByOverview("An orphaned boy enrolls in a school of wizardry");
        assertFalse(movies.isEmpty(), "No movies found!");
        assertTrue(movies.get(0).getOverview().startsWith("An orphaned boy enrolls in a school of wizardry"));

    }

        @Test
    void getMoviesByReleaseDate() throws IOException, InterruptedException {
        List<MovieDTO> movies = ms.getMoviesByReleaseDate("1994-09-09");
        assertFalse(movies.isEmpty(), "No movies found!");
        assertEquals(movies.get(0).getRelease_date(), "1994-09-09");
    }
}