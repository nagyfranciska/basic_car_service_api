package service.model;

import com.google.inject.Inject;
import dao.ServiceDAO;
import exception.general.CDNotFoundException;
import model.Service;
import org.restlet.Response;
import org.restlet.data.Status;

import java.util.List;

public class ServiceService {

    @Inject
    private ServiceDAO serviceDAO;

    @Inject
    private GarageService garageService;

    public ServiceService() {
    }

    public List<Service> getServicesByCar(Integer carId) {
        List<Service> serviceList = serviceDAO.findAllByCar(carId);
        if (serviceList.isEmpty()) {
            Response.getCurrent().setStatus(Status.SUCCESS_NO_CONTENT);
            return null;
        } else {
            return serviceList;
        }
    }

    public List<Service> getServicesByGarage(Integer garageId) {
        List<Service> serviceList = serviceDAO.findAllByGarage(garageId);
        if (serviceList.isEmpty()) {
            Response.getCurrent().setStatus(Status.SUCCESS_NO_CONTENT);
            return null;
        } else  {
            return serviceList;
        }
    }

    //TODO: Save and update methods may be refactored with `if` statement to check for success
    public Service saveService(Service service, Integer garageId) {
        service.setGarage(garageService.getGarageById(garageId));
        Response.getCurrent().setStatus(Status.SUCCESS_CREATED);
        return serviceDAO.save(service);
    }

    public Service getServiceById(Integer serviceId) {
        Service service = serviceDAO.findById(serviceId);
        if (service != null) {
            return service;
        } else {
            throw new CDNotFoundException("Service");
        }
    }

    public Service updateService(Service service, Integer serviceId) {
        return serviceDAO.update(service, serviceId);
    }

    public Service deleteService(Integer serviceId) {
        Service service =serviceDAO.delete(serviceId);
        if (service != null) {
            return service;
        } else {
            throw new CDNotFoundException("Service");
        }
    }

}
