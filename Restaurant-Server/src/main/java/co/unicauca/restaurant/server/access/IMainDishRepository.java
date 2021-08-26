package co.unicauca.restaurant.server.access;

import co.unicauca.restaurant.commons.domain.MainDish;

/**
 * Interface del respositorio de platos
 * @author DANIEL NAVIA-LUIS ARROYO
 */
public interface IMainDishRepository {
    /**
     * Busca un plato por su id
     * @param id cedula del plato
     * @return  objeto de tipo plato
     */
    public MainDish findMainDish(String id);
    
    public String createMainDish(MainDish mainDish);

}
