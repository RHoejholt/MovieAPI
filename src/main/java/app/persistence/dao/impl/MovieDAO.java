import app.entities.Movie;
import app.persistence.dao.IDAO;
import app.persistence.dtos.MovieDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Transactional
public class MovieDAO implements IDAO<MovieDTO> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public MovieDTO save(MovieDTO dto){
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setVoteAverage(0.0); //Default average
        em.persist(movie);
        return new MovieDTO();
    }

    @Override
    public Optional<MovieDTO> findById(int id) {
        return Optional.ofNullable(em.find(Movie.class, id))
                .map(movie -> {
                    MovieDTO dto = new MovieDTO();
                    dto.setTitle(movie.getTitle());
                    return dto;
                });
    }

    @Override
    public List<MovieDTO> findAll() {
        return em.createQuery("SELECT m FROM Movie m", Movie.class)
                .getResultList()
                .stream()
                .map(movie -> {
                    MovieDTO dto = new MovieDTO();
                    dto.setTitle(movie.getTitle());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public MovieDTO update(MovieDTO dto) {
        Movie movie = em.find(Movie.class, dto.getId());
        if (movie != null) {
            movie.setTitle(dto.getTitle());
            em.merge(movie);
            return dto;
        }
        return null;
    }

    @Override
    public void delete(int id) {
        Optional.ofNullable(em.find(Movie.class, id)).ifPresent(em::remove);
    }

}