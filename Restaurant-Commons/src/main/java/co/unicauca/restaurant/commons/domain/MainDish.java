package co.unicauca.restaurant.commons.domain;

/**
 * Clase plato principal
 *
 * @author DANIEL NAVIA-LUIS ARROYO
 */
public class MainDish {

    /**
     * Identificador del plato principal
     */
    private String id_mainDish;
    /**
     * Nombre del plato principal
     */
    private String nameDish;

    /**
     * Precio del plato principal
     */
    private Double dishPrice;

    /**
     * Constructor parametrizado
     *
     * @param id_mainDish Identificador del plato principal
     * @param nameDish Nombre del plato principal
     * @param dishPrice Precio del plato prinicipal
     */
    public MainDish(String id_mainDish, String nameDish, Double dishPrice) {
        this.id_mainDish = id_mainDish;
        this.nameDish = nameDish;
        this.dishPrice = dishPrice;
    }

    /**
     * Constructor por defecto
     */
    public MainDish() {
    }

    //GETER AND SETTER
    public String getId_mainDish() {
        return id_mainDish;
    }

    public void setId_mainDish(String id_mainDish) {
        this.id_mainDish = id_mainDish;
    }

    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }

    public Double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(Double dishPrice) {
        this.dishPrice = dishPrice;
    }

}
