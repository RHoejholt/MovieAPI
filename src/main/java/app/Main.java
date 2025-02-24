package app;
import app.persistence.dtos.MovieDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.core.exc.StreamReadException;


public class Main {

    private static final String apiKey = System.getenv("API_KEY");
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException, InterruptedException {

        String movieId = "139";
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
        } else {
            System.out.println("response failed with code " + response.statusCode());
        }

    }
}