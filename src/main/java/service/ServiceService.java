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

    public Service saveService(Service service, Integer garageId) {
        service.setGarage(garageService.getGarageById(garageId));
        return serviceDAO.save(service);
    }

    public Service updateService(Service service, Integer serviceId) {
        return serviceDAO.update(service, serviceId);
    }

    public Service deleteService(Integer serviceId) {
        return serviceDAO.delete(serviceId);
    }
}
