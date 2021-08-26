package co.unicauca.restaurant.client.access;

import co.unicauca.restaurant.commons.domain.MainDish;

/**
 * Interfaz donde se declara un crud para platos
 *
 * @author DANIEL NAVIA-LUIS ARROYO
 */
public interface IMainDishAccess {

    /**
     * Buscar un plato utilizando un socket
     *
     * @param id identificado del plato principal
     * @return objeto plato
     * @throws Exception error al buscar un plato
     */
    public MainDish findMainDish(String id) throws Exception;

    /**
     * Crea un plato utilizando un socket
     *
     * @param mainDish Plato del restaurante
     * @return Devuelve el id del plato creado
     * @throws Exception error crear el plato
     */
    public String createMainDish(MainDish mainDish) throws Exception;

}
