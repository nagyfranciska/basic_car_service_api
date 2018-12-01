package service;

import com.google.inject.Inject;
import dao.CarDAO;
import model.Car;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarService {

    @Inject
    private CarDAO carDAO;

    @Inject
    private CustomerService customerService;

    @Inject
    private CarValidationService validationService;

    public CarService() {
    }

    public List<Car> getCarsByCustomer(Integer customerId) {
        return carDAO.findAllByCustomer(customerId);
    }

    public Car getCarById(Integer carId) {
        return carDAO.findById(carId);
    }
    
    public Car saveCar(Car car, Integer customerId) {
        if (validationService.carIsValid(car)) {
            car.setCustomer(customerService.getCustomerById(customerId));
            carDAO.save(car);
            return getCarById(car.getId());
        }
        throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Car validation failed");
    }

    public Car updateCar(Car car, Integer carId) {
        if (validationService.carUpdateIsValid(car)) {
            return carDAO.update(car, carId);
        } else {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Car validation failed");
        }
    }

    public Car deleteCar(Integer carId) {
        return carDAO.delete(carId);
    }

}