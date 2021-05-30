package pe.edu.upc.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import pe.edu.upc.entities.Recipe;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Menus")
@Data
public class Menu {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "dateOfRecipe")
    private Date dateOfRecipe;
    /*
     @Column(name = "fecha_publicacion",length = 40, nullable = false)
    private String fechaPublicacion;
     */




}