package pe.edu.upc.services;

import pe.edu.upc.entities.Menu;

public interface MenuService extends CrudService<Menu, Long>{
    Menu getMenuById(Long menuId);
}
