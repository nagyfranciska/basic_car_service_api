package service;

import dao.ServiceDAO;
import model.Service;

import java.util.Map;

public class ServiceService {

    public ServiceDAO serviceDAO;
    private Integer idCount = 0;

    public ServiceService() {
        serviceDAO = new ServiceDAO();
    }

    public Map<Integer, Service> getServices() {
        return serviceDAO.read();
    }

    public Service getServiceById(Integer id) {
        return serviceDAO.read(id);
    }

    public Service saveService(Service service) {
        service.setId(idCount);
        idCount++;
        return serviceDAO.create(service);
    }

    public Service updateService(Service service) {
        return serviceDAO.update(service);
    }

    public Service deleteService(Service service) {
        return serviceDAO.delete(service);
    }
}
