package pe.edu.upc.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.entities.Menu;
import pe.edu.upc.entities.Recipe;
import pe.edu.upc.exception.ResourceNotFoundException;
import pe.edu.upc.repositories.MenuRepository;
import pe.edu.upc.repositories.RecipeRepository;
import pe.edu.upc.services.MenuService;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImplementation implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Transactional
    @Override
    public Menu save(Menu entity) throws Exception {
        return menuRepository.save(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Menu> findAll() throws Exception {
        return menuRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Menu> findById(Long aLong) throws Exception {
        return menuRepository.findById(aLong);
    }

    @Transactional
    @Override
    public Menu update(Menu entity) throws Exception {
        return menuRepository.save(entity);
    }

    @Transactional
    @Override
    public void deleteById(Long aLong) throws Exception {
        menuRepository.deleteById(aLong);
    }

    @Override
    public Menu getMenuById(Long menuId) {
        return menuRepository.findById(menuId).orElseThrow(() -> new ResourceNotFoundException("Menu", "Id", menuId));
    }
}
