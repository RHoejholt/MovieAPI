package app;

import app.services.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


public class Main {


    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException, InterruptedException {


        MovieService ms = new MovieService();


        System.out.println(ms.getLanguageMoviesInLastYears(5, "da"));

        //runManualTesting(ms);
    }

    private static void runManualTesting(MovieService ms) throws IOException, InterruptedException {
        System.out.println(ms.getMovieById("139"));
        System.out.println(ms.getMoviesByOverview("the"));
        System.out.println(ms.getMoviesByReleaseDate("1994-09-09"));
        System.out.println(ms.getMoviesByTitle("Cake"));
    }
}