package app.services;

import app.entities.Movie;
import app.persistence.dtos.MovieDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static app.Main.objectMapper;

public class MovieService {

    private static HttpClient client;
    private static ObjectMapper objectMapper;
    private static String apiKey = System.getenv("API_KEY");

    public MovieService() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();

    }

    public MovieDTO getMovieById(String movieId) throws IOException, InterruptedException {
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


    public List<MovieDTO> getMoviesByTitle(String title) throws IOException, InterruptedException {
        String url = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + URLEncoder.encode(title, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println(response.body());

            JsonNode rootNode = objectMapper.readTree(response.body());
            JsonNode resultsNode = rootNode.path("results");

            List<MovieDTO> movies = objectMapper.readValue(resultsNode.toString(), new TypeReference<List<MovieDTO>>() {});

            System.out.println(movies);
            return movies;
        } else {
            System.out.println("Response failed with code " + response.statusCode());
            return Collections.emptyList();
        }
    }


    public List<MovieDTO> getMoviesByOverview(String keyword) throws IOException, InterruptedException {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println(response.body());

            JsonNode rootNode = objectMapper.readTree(response.body());
            JsonNode resultsNode = rootNode.path("results");

            List<MovieDTO> movies = objectMapper.readValue(resultsNode.toString(), new TypeReference<List<MovieDTO>>() {});

            // Filter movies where the overview contains the keyword
            List<MovieDTO> filteredMovies = movies.stream()
                    .filter(movie -> movie.getOverview() != null && movie.getOverview().toLowerCase().contains(keyword.toLowerCase()))
                    .collect(Collectors.toList());

            System.out.println(filteredMovies);
            return filteredMovies;
        } else {
            System.out.println("Response failed with code " + response.statusCode());
            return Collections.emptyList();
        }
    }

    public List<MovieDTO> getMoviesByReleaseDate(String releaseDate) throws IOException, InterruptedException {
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + apiKey
                + "&primary_release_date.gte=" + releaseDate
                + "&primary_release_date.lte=" + releaseDate;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println(response.body());

            JsonNode rootNode = objectMapper.readTree(response.body());
            JsonNode resultsNode = rootNode.path("results");

            List<MovieDTO> movies = objectMapper.readValue(resultsNode.toString(), new TypeReference<List<MovieDTO>>() {});

            System.out.println(movies);
            return movies;
        } else {
            System.out.println("Response failed with code " + response.statusCode());
            return Collections.emptyList();
        }
    }



}
