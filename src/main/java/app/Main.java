package app;

import app.config.HibernateConfig;
import app.persistence.dao.impl.MovieDAO;
import app.persistence.dtos.MovieDTO;
import app.services.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManagerFactory;

import java.io.IOException;
import java.util.List;


public class Main {


    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException, InterruptedException {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        MovieDAO movieDAO = new MovieDAO(emf.createEntityManager());
        MovieService ms = new MovieService();

        List<MovieDTO> movies = (ms.getLanguageMoviesInLastYears(1, "da"));
        System.out.println(movies);

        movieDAO.save(movies.get(0));
        //runManualTesting(ms);
    }

    private static void runManualTesting(MovieService ms) throws IOException, InterruptedException {
        System.out.println(ms.getMovieById("139"));
        System.out.println(ms.getMoviesByOverview("the"));
        System.out.println(ms.getMoviesByReleaseDate("1994-09-09"));
        System.out.println(ms.getMoviesByTitle("Cake"));
    }
}