package resource.garage.service;

import com.google.inject.Inject;
import model.Service;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import service.ServiceService;

public class ServiceForGarageServerResource extends ServerResource {

    @Inject
    ServiceService serviceService;

    @Get
    public Service getService() {
        return serviceService.getServiceById(Integer.parseInt(getAttribute("serviceId")));
    }

    @Put
    public Service updateService(Service service) {
        Integer garageId = Integer.parseInt(getAttribute("garageId"));
       return serviceService.updateService(garageId, service);
    }

    @Delete
    public Service deleteService() {
        return serviceService.deleteService(Integer.parseInt(getAttribute("serviceId")));
    }
}
