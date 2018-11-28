package resource.garage.service;

import model.Service;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import resource.garage.GaragesServerResource;
import service.GarageService;
import service.ServiceService;

public class ServiceForGarageServerResource extends ServerResource {

    ServiceService serviceService = ServicesForGarageServerResource.serviceService;
    GarageService garageService = GaragesServerResource.garageService;

//    @Get
//    public Service getService() {
//        Integer id = Integer.parseInt(getAttribute("serviceId"));
//        return serviceService.getServiceById(id);
//    }

    @Get
    public Service getService() {
        Integer serviceId = Integer.parseInt(getAttribute("serviceId"));
        Integer garageId = Integer.parseInt(getAttribute("garageId"));
        return garageService.getGarageById(garageId).getServiceList().get(serviceId);
    }

//    @Put
//    public Service updateService(Service service) {
//        return serviceService.updateService(service);
//    }

    @Put
    public Service updateService(Service service) {
        Integer serviceId = Integer.parseInt(getAttribute("serviceId"));
        Integer garageId = Integer.parseInt(getAttribute("garageId"));
        serviceService.updateService(service);
        garageService.getGarageById(garageId).getServiceList().set(serviceId, service);
        return service;
    }

//    @Delete
//    public Service deleteService(Service service) {
//        return serviceService.deleteService(service);
//    }

    @Delete
    public Service deleteService(Service service) {
        Integer serviceId = Integer.parseInt(getAttribute("serviceId"));
        Integer garageId = Integer.parseInt(getAttribute("garageId"));
        serviceService.deleteService(service);
        garageService.getGarageById(garageId).getServiceList().remove(serviceId);
        return service;
    }
}
