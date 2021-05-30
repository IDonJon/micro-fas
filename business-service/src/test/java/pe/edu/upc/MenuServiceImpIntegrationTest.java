package pe.edu.upc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.edu.upc.entities.Menu;
import pe.edu.upc.exception.ResourceNotFoundException;
import pe.edu.upc.repositories.MenuRepository;
import pe.edu.upc.services.MenuService;
import pe.edu.upc.services.impls.MenuServiceImplementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class MenuServiceImpIntegrationTest {

    @MockBean
    private MenuRepository menuRepository;

    @Autowired
    private MenuService menuService;

    @TestConfiguration
    static class MenuServiceImpTestConfiguration {
        @Bean
        public MenuService menuService() {
            return new MenuServiceImplementation();
        }
    }

    @Test
    @DisplayName("When Get Menu By Id With Valid Id Then Returns Menu")
    public void whenGetPublicationByIdWithValidIdThenReturnsPublication() throws ParseException {
        //Arrange
        Menu menu = new Menu();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("28/10/2020");
        menu.setId(1L);
        menu.setDateOfRecipe(date);
        when(menuRepository.findById(1L)).thenReturn(Optional.of(menu));

        //Act
        Menu foundMenu = menuService.getMenuById(1L);

        //Assert
        assertThat(foundMenu.getId()).isEqualTo(1L);
    }


    @Test
    @DisplayName("When GetMenuById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetMenuByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(menuRepository.findById(id)).thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Menu", "Id", id);

        //Act
        Throwable exception = catchThrowable(() -> {
            Menu menu = menuService.getMenuById(id);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class).hasMessage(expectedMessage);
    }
}
