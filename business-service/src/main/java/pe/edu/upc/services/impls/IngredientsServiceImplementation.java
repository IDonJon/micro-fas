package pe.edu.upc.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.entities.Ingredients;
import pe.edu.upc.entities.Recipe;
import pe.edu.upc.exception.ResourceNotFoundException;
import pe.edu.upc.repositories.IngredientsRepository;
import pe.edu.upc.services.IngredientsService;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceImplementation implements IngredientsService {
    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Transactional
    @Override
    public Ingredients save(Ingredients entity) throws Exception {
        return ingredientsRepository.save(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Ingredients> findAll() throws Exception {
        return ingredientsRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Ingredients> findById(Long aLong) throws Exception {
        return ingredientsRepository.findById(aLong);
    }

    @Transactional
    @Override
    public Ingredients update(Ingredients entity) throws Exception {
        return ingredientsRepository.save(entity);
    }

    @Transactional
    @Override
    public void deleteById(Long aLong) throws Exception {
        ingredientsRepository.deleteById(aLong);
    }

    @Override
    public Optional<Ingredients> findbyName(String name) throws Exception {
        return ingredientsRepository.findByName(name);
    }

    @Override
    public Ingredients getIngredientsById(Long ingredientId) {
        return ingredientsRepository.findById(ingredientId).orElseThrow(() -> new ResourceNotFoundException("Ingredient", "Id", ingredientId));
    }

}
