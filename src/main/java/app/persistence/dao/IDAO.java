package app.persistence.dao;

import java.util.List;
import java.util.Optional;

public interface IDAO<T> {
    T save(T dto);
    Optional<T> findById(int id);
    List<T> findAll();
    T update(T dto);
    void delete(int id);
}
