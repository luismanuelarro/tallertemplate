package co.unicauca.restaurant.server.domain.services;

import co.unicauca.restaurant.commons.domain.MainDish;
import co.unicauca.restaurant.commons.infra.JsonError;
import co.unicauca.restaurant.commons.infra.Utilities;
import co.unicauca.restaurant.server.access.IMainDishRepository;
import co.unicauca.restaurant.server.access.MainDishRepositoryImplArrays;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de clientes. Da acceso a la lógica de negocio
 *
 * @author DANIEL NAVIA-LUIS ARROYO
 */
public class MainDishService {

    /**
     * Repositorio de clientes
     */
    IMainDishRepository repo;

    /**
     * Constructor parametrizado. Hace inyeccion de dependencias
     *
     * @param repo repositorio de tipo ICustomerRepository
     */
    public MainDishService(IMainDishRepository repo) {
        this.repo = repo;
    }

    /**
     * Buscar un cliente
     *
     * @param id cedula
     * @return objeto tipo Customer
     */
    public MainDish findMainDish(String id) {
        return repo.findMainDish(id);
    }

    /**
     * Crea un nuevo plato. Aplica validaciones de negocio
     *
     * @param mainDish plato
     * @return devuelve el id del plato creado
     */
    public String createMainDish(MainDish mainDish) {
        List<JsonError> errors = new ArrayList<>();

        // Validaciones y reglas de negocio
        if (mainDish.getId_mainDish().isEmpty() || mainDish.getNameDish().isEmpty()
                || mainDish.getDishPrice() == null) {
            errors.add(new JsonError("400", "BAD_REQUEST", "id, nombres, precio son obligatorios. "));
        }
        // Que no esté repetido

        MainDish mainDishSearched = this.findMainDish(mainDish.getId_mainDish());
        if (mainDishSearched != null) {
            errors.add(new JsonError("400", "BAD_REQUEST", "El id ya existe. "));
        }

        if (!errors.isEmpty()) {
            Gson gson = new Gson();
            String errorsJson = gson.toJson(errors);
            return errorsJson;
        }
        return repo.createMainDish(mainDish);
    }
}
