package pe.edu.upc.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import pe.edu.upc.entities.Recipe;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
@Data
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String unitOfMeasure;

    @Column(length = 40, nullable = false)
    private Long quantity;


    //@NotNull(message = "La categoria no puede ser vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Recipe recipe;
}