package pe.edu.upc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.entities.Ingredients;
import pe.edu.upc.services.IngredientsService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {
    @Autowired
    private IngredientsService ingredientsService;

    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ingredients> fetchById(@PathVariable("id") Long id) {
        try {
            Optional<Ingredients> optionalIngredients = ingredientsService.findById(id);
            if (optionalIngredients.isPresent()) {
                return new ResponseEntity<Ingredients>(optionalIngredients.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ingredients> fetchByName(@PathVariable("name") String name) {
        try {
            Optional<Ingredients> optionalIngredients = ingredientsService.findbyName(name);
            if (optionalIngredients.isPresent()) {
                return ResponseEntity.ok(optionalIngredients.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Ingredients> save(@Valid @RequestBody Ingredients ingredients, BindingResult result) {

        try {
            Ingredients ingredientsCreate = ingredientsService.save(ingredients);
            return ResponseEntity.status(HttpStatus.CREATED).body(ingredientsCreate);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Ingredients> update(@PathVariable("id") Long id, @RequestBody Ingredients ingredients) {

        try {
            Optional<Ingredients> optionalIngredients = ingredientsService.findById(id);
            if (optionalIngredients.isPresent()) {
                Ingredients ingredientsCreate = ingredientsService.save(ingredients);
                return ResponseEntity.status(HttpStatus.CREATED).body(ingredientsCreate);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Ingredients> deleteById(@PathVariable("id") Long id) {


        try {
            Optional<Ingredients> optionalIngredients = ingredientsService.findById(id);
            if (optionalIngredients.isPresent()) {
                ingredientsService.deleteById(id);
                return new ResponseEntity<Ingredients>(HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
