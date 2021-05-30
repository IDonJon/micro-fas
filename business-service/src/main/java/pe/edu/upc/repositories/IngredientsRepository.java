package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.entities.Ingredients;

import java.util.Optional;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
    Optional<Ingredients> findByName(String name);
}
