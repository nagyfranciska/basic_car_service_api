package resource.customer.car.service;

import model.Service;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import service.ServiceService;

import java.util.List;

public class ServicesForCarServerResource extends ServerResource {

    //TODO: Solve logic without instantiation
    public static ServiceService serviceService = new ServiceService();

    @Get
    public List<Service> getServices() {
        Integer carId = Integer.parseInt(getAttribute("carId"));
        Integer customerId = Integer.parseInt(getAttribute("customerId"));
        return serviceService.getServicesByCarAndCustomer(customerId, carId);
    }

}
