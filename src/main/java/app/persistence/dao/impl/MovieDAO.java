package app.persistence.dao.impl;

import app.entities.Movie;
import app.persistence.dao.IDAO;
import app.persistence.dtos.MovieDTO;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class MovieDAO implements IDAO<MovieDTO> {

    private EntityManager em;

    public MovieDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public MovieDTO save(MovieDTO dto){
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setVoteAverage(0.0); //Default average
        em.getTransaction().begin();
        em.persist(movie);
        em.getTransaction().commit();
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
            em.getTransaction().begin();
            em.merge(movie);
            em.getTransaction().commit();
            return dto;
        }
        return null;
    }

    @Override
    public void delete(int id) {
        em.getTransaction().begin();
        Optional.ofNullable(em.find(Movie.class, id)).ifPresent(em::remove);
        em.getTransaction().commit();
    }

}