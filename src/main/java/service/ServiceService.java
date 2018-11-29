package service;

import dao.ServiceDAO;
import model.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServiceService {

    private ServiceDAO serviceDAO;
    private GarageService garageService;

    //TODO: Solve logic without instantiation
    public ServiceService() {
        serviceDAO = new ServiceDAO();
    }

    public List<Service> getServicesByCarAndCustomer(Integer customerId, Integer carId) {
        List rawList = serviceDAO.findAllByCarIdAndCustomerId(customerId, carId);
        List<Service> serviceList = new CopyOnWriteArrayList<>();
        try {
            rawList.forEach(s -> serviceList.add((Service) s));
        } catch (TypeNotPresentException e) {
            System.out.println("error in ServiceService with serviceList (ByCarAndCustomer)");
        }
        return serviceList;
    }

    public List<Service> getServicesByGarage(Integer garageId) {
        List rawList = serviceDAO.findAllByGarage(garageId);
        List<Service> serviceList = new CopyOnWriteArrayList<>();
        try {
            rawList.forEach(s -> serviceList.add((Service) s));
        } catch (TypeNotPresentException e) {
            System.out.println("error in ServiceService with serviceList(ByGarage)");
        }
        return serviceList;
    }

    public Service getServiceByIdByGarage(Integer garageId, Integer serviceId) {
        return (Service) serviceDAO.findByIdAndGarageId(garageId, serviceId);
    }

    public Service saveService(Integer garageId, Service service) {
        service.setGarage(garageService.getGarageById(garageId));
        serviceDAO.save(service);
        return (Service)serviceDAO.findByIdAndGarageId(garageId, service.getId());
    }

    public Service getServiceById(Integer id) {
        return serviceDAO.findById(id);
    }

    public Service updateService(Integer garageId, Service service) {
        serviceDAO.update(service);
        garageService.getGarageById(garageId).getServiceList().set(service.getId(), service);
        return (Service)serviceDAO.findByIdAndGarageId(garageId, service.getId());
    }

    public Service deleteService(Integer garageId, Service service) {
        Service deletedService = (Service)serviceDAO.findByIdAndGarageId(garageId, service.getId());
        int serviceId = service.getId();
        garageService.getGarageById(garageId).getServiceList().remove(serviceId);
        serviceDAO.delete(service);
        return deletedService;
    }
}
