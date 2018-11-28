package service;

import dao.ServiceDAO;
import model.Service;

import java.util.List;
import java.util.Map;

public class ServiceService {

    public ServiceDAO serviceDAO;

    public ServiceService() {
        serviceDAO = new ServiceDAO();
    }

    public List getServices() {
        return serviceDAO.findAll();
    }

    public Service getServiceById(Integer id) {
        return serviceDAO.findById(id);
    }

    public Service saveService(Service service) {
        serviceDAO.save(service);
        return serviceDAO.findById(service.getId());
    }

    public Service updateService(Service service) {
        serviceDAO.update(service);
        return serviceDAO.findById(service.getId());
    }

    public Service deleteService(Service service) {
        serviceDAO.delete(service);
        return service;
    }
}
