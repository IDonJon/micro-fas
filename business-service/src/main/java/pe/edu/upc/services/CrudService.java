package pe.edu.upc.services;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {
    T save(T entity) throws Exception;
    List<T> findAll() throws Exception;
    Optional<T> findById(ID id) throws Exception;
    T update(T entity) throws Exception;
    void deleteById(ID id) throws Exception;
}
