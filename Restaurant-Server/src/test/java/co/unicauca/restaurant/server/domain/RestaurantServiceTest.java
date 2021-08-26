
package co.unicauca.restaurant.server.domain;

import co.unicauca.restaurant.server.domain.services.CustomerService;
import co.unicauca.restaurant.server.domain.services.MainDishService;
import co.unicauca.restaurant.commons.domain.Customer;
import co.unicauca.restaurant.commons.domain.MainDish;
import co.unicauca.restaurant.server.access.Factory;
import co.unicauca.restaurant.server.access.ICustomerRepository;
import co.unicauca.restaurant.server.access.IMainDishRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Libardo, Julio
 */
public class RestaurantServiceTest 
{

    @Test
    public void testAddDish() 
    {
        System.out.println("addDish");
        
        MainDish dish = new MainDish();
        dish.setId_mainDish("123");
        dish.setNameDish("Cocacola");
        dish.setDishPrice(5000.0);
        
        IMainDishRepository repo = Factory.getInstance().getRepositoryMD();
        MainDishService instance = new MainDishService(repo);
        String expResult = "123";
        String result = instance.createMainDish(dish);
        assertEquals(expResult, result);
        
        // Validar un dato erroneo
        dish.setNameDish("");
        result = instance.createMainDish(dish);
        assertTrue(result.contains("BAD_REQUEST")); 
    }
    
    @Test
    public void testFindDish() {
        System.out.println("Encontrar Dish ");
        String id = "98000001";
        IMainDishRepository repo = Factory.getInstance().getRepositoryMD();
        MainDishService instance = new MainDishService(repo);
        String expResult = "98000001";
        MainDish result = instance.findMainDish(id);
        assertEquals(expResult, result.getId_mainDish());
        
        // Customer que no existe
        result = instance.findMainDish("12345678");
        assertEquals(null, result);

    }
}
