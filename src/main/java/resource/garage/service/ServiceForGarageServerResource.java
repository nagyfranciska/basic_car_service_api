package resource.garage.service;

import model.Service;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import service.ServiceService;

public class ServiceForGarageServerResource extends ServerResource {

    private ServiceService serviceService = ServicesForGarageServerResource.serviceService;

    @Get
    public Service getService() {
        Integer serviceId = Integer.parseInt(getAttribute("serviceId"));
        Integer garageId = Integer.parseInt(getAttribute("garageId"));
        return serviceService.getServiceByIdByGarage(garageId, serviceId);
    }


    @Put
    public Service updateService(Service service) {
        Integer garageId = Integer.parseInt(getAttribute("garageId"));
       return serviceService.updateService(garageId, service);
    }

    @Delete
    public Service deleteService(Service service) {
        Integer garageId = Integer.parseInt(getAttribute("garageId"));
        return serviceService.deleteService(garageId, service);
    }
}
