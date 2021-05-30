package pe.edu.upc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.edu.upc.entities.Ingredients;
import pe.edu.upc.exception.ResourceNotFoundException;
import pe.edu.upc.repositories.IngredientsRepository;
import pe.edu.upc.services.IngredientsService;
import pe.edu.upc.services.impls.IngredientsServiceImplementation;

import java.text.ParseException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class IngredientServiceImpIntegrationTest {

    @MockBean
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private IngredientsService ingredientsService;

    @TestConfiguration
    static class IngredientsServiceImpTestConfiguration {
        @Bean
        public IngredientsService ingredientsService() {
            return new IngredientsServiceImplementation();
        }
    }

    @Test
    @DisplayName("When Get Ingredients By Id With Valid Id Then Returns Ingredients")
    public void whenGetIngredientsByIdWithValidIdThenReturnsIngredients() throws ParseException {
        //Arrange
        String title = "Ají amarillo";
        Ingredients ingredients = new Ingredients();
        ingredients.setId(5L);
        ingredients.setName(title);
        ingredients.setQuantity(10L);
        when(ingredientsRepository.findById(5L)).thenReturn(Optional.of(ingredients));
        //Act
        Ingredients foundIngredients = ingredientsService.getIngredientsById(5L);
        //Assert
        assertThat(foundIngredients.getId()).isEqualTo(5L);
    }

    @Test
    @DisplayName("When Get Ingredient Id With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetIngredientByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        //Arrange
        Long id = 5L;
        String template = "Resource %s not found for %s with value %s";
        when(ingredientsRepository.findById(id)).thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Ingredient", "Id", id);

        //Act
        Throwable exception = catchThrowable(() -> {
            Ingredients ingredient = ingredientsService.getIngredientsById(id);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class).hasMessage(expectedMessage);
    }
}
