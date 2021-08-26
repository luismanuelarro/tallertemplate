package co.unicauca.restaurant.server.access;

import co.unicauca.restaurant.commons.domain.MainDish;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de IMainDishRepository. Utilliza arreglos en memoria
 *
 * @author DANIEL NAVIA-LUIS ARROYO
 */
public final class MainDishRepositoryImplArrays implements IMainDishRepository {

    /**
     * Array List de clientes
     */
    private static List<MainDish> dishes;

    public MainDishRepositoryImplArrays() {
        if (dishes == null){
            dishes = new ArrayList();
        }
        
        if (dishes.size() == 0){
            inicializar();
        }
    }

    public void inicializar() {
        dishes.add(new MainDish("1", "Plato 1", 110000d));
        dishes.add(new MainDish("2", "Plato 2", 120000d));
        dishes.add(new MainDish("3", "Plato 3", 130000d));
        dishes.add(new MainDish("4", "Plato 4", 140000d));
        dishes.add(new MainDish("5", "Plato 5", 150000d));
        dishes.add(new MainDish("6", "Plato 6", 160000d));
        dishes.add(new MainDish("7", "Plato 7", 170000d));
        dishes.add(new MainDish("8", "Plato 8", 180000d));
        dishes.add(new MainDish("9", "Plato 9", 190000d));
        dishes.add(new MainDish("10", "Plato 10", 100000d));

    }

    /**
     * Busca u Customer en el arreglo
     *
     * @param id cedula del customer
     * @return objeto Customer
     */
    @Override
    public MainDish findMainDish(String id) {
        for (MainDish mainDish : dishes) {
            if (mainDish.getId_mainDish().equals(id)) {
                return mainDish;
            }
        }
        return null;
    }

    @Override
    public String createMainDish(MainDish mainDish) {
        dishes.add(mainDish);
        return mainDish.getId_mainDish();
    }

}
