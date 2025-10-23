package ma.beans.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {
    T save(T entity);
    Optional<T> findById(Long id);
    List<T> findAll();
    T update(T entity);
    void delete(Long id);
}
