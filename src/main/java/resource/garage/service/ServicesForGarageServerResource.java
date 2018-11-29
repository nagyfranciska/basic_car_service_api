package resource.garage.service;

import model.Service;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import resource.customer.car.service.ServicesForCarServerResource;
import resource.garage.GaragesServerResource;
import service.GarageService;
import service.ServiceService;

import java.util.List;
import java.util.Map;

public class ServicesForGarageServerResource extends ServerResource {

    static ServiceService serviceService = ServicesForCarServerResource.serviceService;
    private GarageService garageService = GaragesServerResource.garageService;

    @Get
    public List<Service> getServices() {
        Integer garageId = Integer.parseInt(getAttribute("garageId"));
        return garageService.getGarageById(garageId).getServiceList();
    }

    @Post
    public Service saveService(Service service) {
        Integer garageId = Integer.parseInt(getAttribute("garageId"));
        serviceService.saveService(service);
        garageService.getGarageById(garageId).getServiceList().add(service);
        return service;
    }
}
