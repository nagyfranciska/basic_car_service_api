package resource.service;

import com.google.inject.Inject;
import model.Service;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import service.model.ServiceService;

import java.util.List;

public class ServicesByCarServerResource extends ServerResource {

    @Inject
    private ServiceService serviceService;

    @Get
    public List<Service> getServices() {
        return serviceService.getServicesByCar(Integer.parseInt(getAttribute("carId")));
    }

}
