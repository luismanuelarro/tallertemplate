package co.unicauca.restaurant.server.infra;

import co.unicauca.serversocket.serversockettemplate.infra.ServerSocketTemplate;
import co.unicauca.restaurant.commons.domain.Customer;
import co.unicauca.restaurant.commons.domain.MainDish;
import co.unicauca.restaurant.commons.infra.JsonError;
import co.unicauca.restaurant.commons.infra.Protocol;
import co.unicauca.restaurant.commons.infra.Utilities;
import co.unicauca.restaurant.server.access.Factory;
import co.unicauca.restaurant.server.domain.services.CustomerService;
import co.unicauca.restaurant.server.access.ICustomerRepository;
import co.unicauca.restaurant.server.access.IMainDishRepository;
import co.unicauca.restaurant.server.domain.services.MainDishService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 * Servidor Socket que está escuchando permanentemente solicitudes de los
 * clientes. Cada solicitud la atiende en un hilo de ejecución
 *
 * @author DANIEL NAVIA-LUIS ARROYO
 */
public class ServerSocketRestaurant extends ServerSocketTemplate {

    /**
     * Servicio de clientes
     */
    private CustomerService service;
    /**
     * Servicio de platos principales
     */
    private MainDishService mdService;

    /**
     * Constructor
     */
    public ServerSocketRestaurant() {

    }


    /**
     * Procesar la solicitud que proviene de la aplicación cliente
     *
     * @param requestJson petición que proviene del cliente socket en formato
     * json que viene de esta manera:
     * "{"resource":"customer","action":"get","parameters":[{"name":"id","value":"1"}]}"
     *
     */
    @Override
    protected void processRequest(String requestJson) {
        // Convertir la solicitud a objeto Protocol para poderlo procesar
        Gson gson = new Gson();
        Protocol protocolRequest = gson.fromJson(requestJson, Protocol.class);

        switch (protocolRequest.getResource()) {
            case "customer":
                if (protocolRequest.getAction().equals("get")) {
                    // Consultar un customer
                    processGetCustomer(protocolRequest);
                }

                if (protocolRequest.getAction().equals("post")) {
                    // Agregar un customer    
                    processPostCustomer(protocolRequest);

                }
                break;
            case "maindish":
                if (protocolRequest.getAction().equals("get")) {
                    // Consultar un plao
                    processGetMainDish(protocolRequest);
                }

                if (protocolRequest.getAction().equals("post")) {
                    // Agregar un plato    
                    processPostMainDish(protocolRequest);
                }
                break;
        }

    }
    
    
    
    /**
     * Inicialización
     *
     * @return este mismo objeto
     */
    @Override
    protected ServerSocketTemplate init() {
        PORT = Integer.parseInt(Utilities.loadProperty("server.port"));
        // Se hace la inyección de dependencia
        ICustomerRepository repository = Factory.getInstance().getRepository();
        IMainDishRepository mdRepository = Factory.getInstance().getRepositoryMD();
        this.setService(new CustomerService(repository));
        this.setServiceMD(new MainDishService(mdRepository));
        return this;
    }

    /**
     * Procesa la solicitud de consultar un customer
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private void processGetCustomer(Protocol protocolRequest) {
        // Extraer la cedula del primer parámetro
        String id = protocolRequest.getParameters().get(0).getValue();
        Customer customer = getService().findCustomer(id);
        if (customer == null) {
            String errorJson = generateNotFoundErrorJson();
            respond(errorJson);
        } else {
            respond(objectToJSON(customer));
        }
    }

    /**
     * Procesa la solicitud de consultar un plato principal
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private void processGetMainDish(Protocol protocolRequest) {
        // Extraer el identificador del primer parámetro
        String id = protocolRequest.getParameters().get(0).getValue();
        MainDish mainDish = mdService.findMainDish(id);
        if (mainDish == null) {
            String errorJson = generateNotFoundErrorJson();
            respond(errorJson);
        } else {
            respond(objectToJSON(mainDish));
        }
    }

    /**
     * Procesa la solicitud de agregar un customer
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private void processPostCustomer(Protocol protocolRequest) {
        Customer customer = new Customer();
        // Reconstruir el customer a partid de lo que viene en los parámetros
        customer.setId(protocolRequest.getParameters().get(0).getValue());
        customer.setFirstName(protocolRequest.getParameters().get(1).getValue());
        customer.setLastName(protocolRequest.getParameters().get(2).getValue());
        customer.setAddress(protocolRequest.getParameters().get(3).getValue());
        customer.setEmail(protocolRequest.getParameters().get(4).getValue());
        customer.setGender(protocolRequest.getParameters().get(5).getValue());
        customer.setMobile(protocolRequest.getParameters().get(6).getValue());

        String response = getService().createCustomer(customer);
        respond(response);
    }

    /**
     * Procesa la solicitud de agregar un plato
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private void processPostMainDish(Protocol protocolRequest) {
        MainDish mainDish = new MainDish();
        // Reconstruir el customer a partid de lo que viene en los parámetros
        mainDish.setId_mainDish(protocolRequest.getParameters().get(0).getValue());
        mainDish.setNameDish(protocolRequest.getParameters().get(1).getValue());
        mainDish.setDishPrice(Double.parseDouble(protocolRequest.getParameters().get(2).getValue()));

        String response = getServiceMD().createMainDish(mainDish);
        respond(response);
    }

    /**
     * Genera un ErrorJson no encontrado
     *
     * @return error en formato json
     */
    private String generateNotFoundErrorJson() {
        List<JsonError> errors = new ArrayList<>();
        JsonError error = new JsonError();
        error.setCode("404");
        error.setError("NOT_FOUND");
        error.setMessage("ERROR: Id no existe");
        errors.add(error);

        Gson gson = new Gson();
        String errorsJson = gson.toJson(errors);

        return errorsJson;
    }

    /**
     * @return the service
     */
    public CustomerService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(CustomerService service) {
        this.service = service;
    }

    /**
     * @return the service
     */
    public MainDishService getServiceMD() {
        return mdService;
    }

    /**
     * @param service the service to set
     */
    public void setServiceMD(MainDishService service) {
        this.mdService = service;
    }
}
