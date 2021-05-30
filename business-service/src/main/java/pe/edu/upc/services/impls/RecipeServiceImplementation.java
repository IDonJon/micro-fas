package pe.edu.upc.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.entities.Recipe;
import pe.edu.upc.exception.ResourceNotFoundException;
import pe.edu.upc.repositories.RecipeRepository;
import pe.edu.upc.services.RecipeService;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImplementation implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Transactional
    @Override
    public Recipe save(Recipe entity) throws Exception {
        return recipeRepository.save(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Recipe> findAll() throws Exception {
        return recipeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Recipe> findById(Long aLong) throws Exception {
        return recipeRepository.findById(aLong);
    }

    @Transactional
    @Override
    public Recipe update(Recipe entity) throws Exception {
        return recipeRepository.save(entity);
    }

    @Transactional
    @Override
    public void deleteById(Long aLong) throws Exception {
        recipeRepository.deleteById(aLong);
    }

    @Override
    public Optional<Recipe> findbyTitle(String title) throws Exception {
        return recipeRepository.findByTitle(title);
    }

    @Override
    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Recipe", "Id", recipeId));
    }
}
