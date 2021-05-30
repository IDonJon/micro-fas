package pe.edu.upc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.entities.Recipe;
import pe.edu.upc.services.RecipeService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recipe> fetchById(@PathVariable("id") Long id) {
        try {
            Optional<Recipe> optionalRecipe = recipeService.findById(id);
            if (optionalRecipe.isPresent()) {
                return new ResponseEntity<Recipe>(optionalRecipe.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Falta Title
    //Falta Name
    @GetMapping(path = "/title/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recipe> fetchById(@PathVariable("title") String title) {
        try {
            Optional<Recipe> optionalRecipe = recipeService.findbyTitle(title);
            if (optionalRecipe.isPresent()) {
                return ResponseEntity.ok(optionalRecipe.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Recipe> save(@Valid @RequestBody Recipe recipe, BindingResult result) {

        try {
            Recipe recipeCreate = recipeService.save(recipe);
            return ResponseEntity.status(HttpStatus.CREATED).body(recipeCreate);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Recipe> update(@PathVariable("id") Long id, @RequestBody Recipe recipe) {

        try {
            Optional<Recipe> optionalRecipe = recipeService.findById(id);
            if (optionalRecipe.isPresent()) {
                Recipe menuCreate = recipeService.save(recipe);
                return ResponseEntity.status(HttpStatus.CREATED).body(menuCreate);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Recipe> deleteById(@PathVariable("id") Long id) {


        try {
            Optional<Recipe> optionalRecipe = recipeService.findById(id);
            if (optionalRecipe.isPresent()) {
                recipeService.deleteById(id);
                return new ResponseEntity<Recipe>(HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
