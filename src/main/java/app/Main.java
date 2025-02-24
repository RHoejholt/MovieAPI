package app;
import app.persistence.dtos.MovieDTO;
import app.services.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.core.exc.StreamReadException;


public class Main {


    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException, InterruptedException {


        MovieService ms = new MovieService();

        ms.getMovieById("139");
        ms.getMoviesByOverview("kriger");
        ms.getMoviesByReleaseDate("1994-09-09");
        ms.getMoviesByTitle("Cake");
    }
}