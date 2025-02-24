package app.services;

import app.entities.Movie;
import app.persistence.dtos.MovieDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static app.Main.objectMapper;

public class MovieService {

    private static HttpClient client;
    private static ObjectMapper objectMapper;

    public MovieService() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public MovieDTO getMovieById(String movieId, String apiKey) throws IOException, InterruptedException {
        String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            System.out.println(response.body());

            MovieDTO movie = objectMapper.readValue(response.body(), MovieDTO.class);

            System.out.println(movie);
            return movie;
        } else {
            System.out.println("response failed with code " + response.statusCode());
            return null;
        }

    }

}
