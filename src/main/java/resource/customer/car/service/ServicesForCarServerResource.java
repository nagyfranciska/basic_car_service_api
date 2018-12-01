package resource.customer.car.service;

import com.google.inject.Inject;
import model.Service;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import service.ServiceService;

import java.util.List;

public class ServicesForCarServerResource extends ServerResource {

    @Inject
    ServiceService serviceService;

    @Get
    public List<Service> getServices() {
        return serviceService.getServicesByCar(Integer.parseInt(getAttribute("carId")));
    }

}
