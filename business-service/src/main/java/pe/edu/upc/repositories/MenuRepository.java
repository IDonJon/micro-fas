package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.entities.Menu;
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

}
