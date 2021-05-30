package pe.edu.upc.services;

import pe.edu.upc.entities.Ingredients;

import java.util.Optional;

public interface IngredientsService extends CrudService<Ingredients, Long>{
    Optional<Ingredients> findbyName(String name)throws Exception;
    Ingredients getIngredientsById(Long aLong);
}
