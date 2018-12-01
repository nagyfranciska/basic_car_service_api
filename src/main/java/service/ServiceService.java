package service;

import com.google.inject.Inject;
import dao.ServiceDAO;
import model.Service;

import java.util.List;

public class ServiceService {

    @Inject
    private ServiceDAO serviceDAO;
    @Inject
    private GarageService garageService;

    public ServiceService() {}

    public List<Service> getServicesByCar(Integer carId) {
        return serviceDAO.findAllByCar(carId);
    }

    public List<Service> getServicesByGarage(Integer garageId) {
        return serviceDAO.findAllByGarage(garageId);
    }

    public Service getServiceById(Integer serviceId) {
        return serviceDAO.findById(serviceId);
    }

    public Service saveService(Integer garageId, Service service) {
        service.setGarage(garageService.getGarageById(garageId));
        serviceDAO.save(service);
        return serviceDAO.findById(service.getId());
    }

    public Service updateService(Integer garageId, Service service) {
        serviceDAO.update(service);
        garageService.getGarageById(garageId).getServiceList().add(service);
        return serviceDAO.findById(service.getId());
    }

    public Service deleteService(Integer serviceId) {
        return serviceDAO.delete(serviceId);
    }
}
