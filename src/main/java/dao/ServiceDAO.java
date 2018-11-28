package dao;

import model.Service;

import java.util.HashMap;
import java.util.Map;

public class ServiceDAO {

    public static Map<Integer, Service> mapOfServices;

    public ServiceDAO() {
        mapOfServices = new HashMap<>();
    }

    public Map<Integer, Service> read() {
        return mapOfServices;
    }

    public Service read(Integer id) {
        return mapOfServices.get(id);
    }

    public Service create(Service service) {
        return mapOfServices.put(service.getId(), service);
    }

    public Service delete(Service service) {
        return mapOfServices.remove(service.getId());
    }

    public Service update(Service service) {
        return mapOfServices.put(service.getId(), service);
    }
}
