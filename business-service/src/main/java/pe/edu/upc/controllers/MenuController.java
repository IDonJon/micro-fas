package pe.edu.upc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.entities.Menu;
import pe.edu.upc.services.MenuService;


import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> fetchById(@PathVariable("id") Long id) {
        try {
            Optional<Menu> optionalMenu = menuService.findById(id);
            if(optionalMenu.isPresent()){
                return new ResponseEntity<Menu>(optionalMenu.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Menu> save(@Valid @RequestBody Menu menu, BindingResult result) {

        try {
            Menu menuCreate = menuService.save(menu);
            return ResponseEntity.status(HttpStatus.CREATED).body(menuCreate);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Menu> update(@PathVariable("id") Long id, @RequestBody Menu menu) {

        try {
            Optional<Menu> optionalMenu = menuService.findById(id);
            if (optionalMenu.isPresent()) {
                Menu menuCreate = menuService.save(menu);
                return ResponseEntity.status(HttpStatus.CREATED).body(menuCreate);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Menu> deleteById(@PathVariable("id") Long id){


        try {
            Optional<Menu> optionalMenu = menuService.findById(id);
            if(optionalMenu.isPresent()){
                menuService.deleteById(id);
                return new ResponseEntity<Menu>(HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
