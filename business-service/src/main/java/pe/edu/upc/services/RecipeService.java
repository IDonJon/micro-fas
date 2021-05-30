package pe.edu.upc.services;

import pe.edu.upc.entities.Recipe;

import java.util.Optional;

public interface RecipeService extends CrudService<Recipe, Long> {
    Optional<Recipe> findbyTitle(String title) throws Exception;

    Recipe getRecipeById(Long recipeId);
}
