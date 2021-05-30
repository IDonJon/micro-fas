package pe.edu.upc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.edu.upc.entities.Recipe;
import pe.edu.upc.exception.ResourceNotFoundException;
import pe.edu.upc.repositories.RecipeRepository;
import pe.edu.upc.services.RecipeService;
import pe.edu.upc.services.impls.RecipeServiceImplementation;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class RecipeServiceImpIntegrationTest {

    @MockBean
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeService recipeService;

    @TestConfiguration
    static class RecipeServiceImpTestConfiguration {
        @Bean
        public RecipeService recipeService() {
            return new RecipeServiceImplementation();
        }
    }

    @Test
    @DisplayName("When Get Recipe By Id With Valid Id Then Returns Recipe")
    public void whenGetRecipeByIdWithValidIdThenReturnsRecipe() {
        //Arrange
        String title = "Causa de pollo";
        String description = "La mejor causa de pollo de todo Lima";
        String content = "The best causita de pollo";
        Recipe recipe = new Recipe();
        recipe.setId(10L);
        recipe.setTitle(title);
        recipe.setDescription(description);
        recipe.setContent(content);

        when(recipeRepository.findById(10L)).thenReturn(Optional.of(recipe));
        //Act
        Recipe foundRecipe = recipeService.getRecipeById(10L);

        //Assert
        assertThat(foundRecipe.getId()).isEqualTo(10L);
    }

    @Test
    @DisplayName("When Get Recipe By Id With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetRecipeByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 15L;
        String title = "Rocoto relleno";
        String template = "Resource %s not found for %s with value %s";
        when(recipeRepository.findById(id)).thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Recipe", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            Recipe recipe = recipeService.getRecipeById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }
}
