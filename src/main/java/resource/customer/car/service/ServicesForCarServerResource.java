package resource.customer.car.service;

import model.Service;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import resource.customer.CustomersServerResource;
import service.CustomerService;
import service.ServiceService;

import java.util.List;


public class ServicesForCarServerResource extends ServerResource {

    public static ServiceService serviceService = new ServiceService();
    CustomerService customerService = CustomersServerResource.customerService;

//    @Get
//    public Map<Integer, Service> getServices() {
//        return serviceService.getServices();
//    }

    @Get
    public List<Service> getServices() {
        Integer carId = Integer.parseInt(getAttribute("carId"));
        Integer customerId = Integer.parseInt(getAttribute("customerId"));
        return null;
    }

}
