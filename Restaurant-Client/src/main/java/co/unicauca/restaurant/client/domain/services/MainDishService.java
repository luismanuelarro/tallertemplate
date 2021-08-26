package co.unicauca.restaurant.client.domain.services;

import co.unicauca.restaurant.client.access.IMainDishAccess;
import co.unicauca.restaurant.commons.domain.MainDish;
import java.util.List;

/**
 * Es una fachada para comunicar la presentación con el dominio
 *
 * @author DANIEL NAVIA-LUIS ARROYO
 */
public class MainDishService {

    private final IMainDishAccess service;

    /**
     * Constructor privado que evita que otros objetos instancien
     *
     * @param service implementacion de tipo IMainDishService
     */
    public MainDishService(IMainDishAccess service) {
        this.service = service;
    }

    /**
     * Busca un plato en el servidor remoto
     *
     * @param id identificador del plato
     * @return Objeto tipo Plato, null si no lo encuentra
     * @throws java.lang.Exception la excepcio se lanza cuando no logra conexión
     * con el servidor
     */
    public MainDish findMainDish(String id) throws Exception {
        return service.findMainDish(id);

    }

    /**
     * Crea un plato en el servidor remoto
     *
     * @param mainDish objeto de tipo mainDish
     * @return true si se creo correctamente o false en caso contrario
     * @throws Exception la excepcio se lanza cuando no logra conexión con el
     * servidor
     */
    public String createMainDish(MainDish mainDish) throws Exception {
        return service.createMainDish(mainDish);

    }

}
