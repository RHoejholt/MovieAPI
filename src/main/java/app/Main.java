package app;
import app.config.HibernateConfig;
import app.persistence.dao.impl.MovieDAO;
import app.persistence.dtos.MovieDTO;
import app.services.MovieService;
import jakarta.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        MovieDAO movieDAO = new MovieDAO(emf.createEntityManager());
        MovieService ms = new MovieService();

        //only needs to be run once
        //populateDataBase(ms, movieDAO);

        Optional<MovieDTO> movie1 = movieDAO.findById(1);
        if(movie1.isPresent());
        {
            MovieDTO movieDTO1 = movie1.get();
            movieDTO1.setTitle("half life 3");
            movieDAO.update(movieDTO1);
        }

        movieDAO.delete(100);

        List<MovieDTO> movies = movieDAO.findAll();
        String searchName = "Copenhagen";
        List<MovieDTO> filteredMovies = movies.stream()
                .filter(movie -> movie.getTitle() != null && movie.getTitle().toLowerCase().contains(searchName.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println(filteredMovies);

        //runManualTesting(ms);
    }

    private static void populateDataBase(MovieService ms, MovieDAO movieDAO) throws IOException, InterruptedException {
        List<MovieDTO> movies = (ms.getLanguageMoviesInLastYears(5, "da"));
        System.out.println(movies);

        for(MovieDTO movie : movies) {
            movieDAO.save(movie);
        }
    }

    private static void runManualTesting(MovieService ms) throws IOException, InterruptedException {
        System.out.println(ms.getMovieById("139"));
        System.out.println(ms.getMoviesByOverview("the"));
        System.out.println(ms.getMoviesByReleaseDate("1994-09-09"));
        System.out.println(ms.getMoviesByTitle("Cake"));
    }
}