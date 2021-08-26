package co.unicauca.restaurant.server.access;

import co.unicauca.restaurant.commons.infra.Utilities;

/**
 * Fabrica que se encarga de instanciar un repositorio concreto
 *
 * @author DANIEL NAVIA-LUIS ARROYO
 */
public class Factory {

    private static Factory instance;

    private Factory() {
    }

    /**
     * Clase singleton
     *
     * @return
     */
    public static Factory getInstance() {

        if (instance == null) {
            instance = new Factory();
        }
        return instance;

    }

    /**
     * Método que crea una instancia concreta de la jerarquia
     * ICustomerRepository
     *
     * @return una clase hija de la abstracción IRepositorioClientes
     */
    public ICustomerRepository getRepository() {
        String type = Utilities.loadProperty("customer.repository");
        if (type.isEmpty()) {
            type = "default";
        }
        ICustomerRepository result = null;

        switch (type) {
            case "default":
                result = new CustomerRepositoryImplArrays();
                break;
            case "mysql":
                result = new CustomerRepositoryImplMysql();
                break;
        }

        return result;
    }

    /**
     * Método que crea una instancia concreta de la jerarquia
     * IMainDishRepository
     *
     * @return una clase hija de la abstracción IRepositorioPlatos
     */
    public IMainDishRepository getRepositoryMD() {
        String type = Utilities.loadProperty("maindish.repository");
        if (type.isEmpty()) {
            type = "default";
        }
        IMainDishRepository result = null;

        switch (type) {
            case "default":
                result = new MainDishRepositoryImplArrays();
                break;
        }

        return result;
    }
}
