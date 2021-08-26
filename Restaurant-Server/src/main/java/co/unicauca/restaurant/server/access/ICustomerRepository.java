package co.unicauca.restaurant.server.access;

import co.unicauca.restaurant.commons.domain.Customer;

/**
 * Interface del respositorio de clientes
 * @author DANIEL NAVIA-LUIS ARROYO
 */
public interface ICustomerRepository {
    /**
     * Busca un Customer por su ceduka
     * @param id cedula del cliente
     * @return  objeto de tipo Customer
     */
    public Customer findCustomer(String id);
    
    public String createCustomer(Customer customer);

}
