package service;

import com.google.inject.Inject;
import dao.CarDAO;
import exception.general.CDNotFoundException;
import model.Car;
import org.restlet.Response;
import org.restlet.data.Status;
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
        List<Car> carList = carDAO.findAllByCustomer(customerId);
        if (carList.isEmpty()) {
            Response.getCurrent().setStatus(Status.SUCCESS_NO_CONTENT);
            return null;
        } else {
            return carList;
        }
    }

    public Car saveCar(Car car, Integer customerId) {
        if (validationService.carIsValid(car)) {
            car.setCustomer(customerService.getCustomerById(customerId));
            Response.getCurrent().setStatus(Status.SUCCESS_CREATED);
            return carDAO.save(car);
        } else {
            return null;
        }
    }

    public Car getCarById(Integer carId) {
        Car car = carDAO.findById(carId);
        if (car != null) {
            return car;
        } else {
            throw new CDNotFoundException("Car");
        }
    }

    public Car updateCar(Car car, Integer carId) {
        if (validationService.carUpdateIsValid(car)) {
            return carDAO.update(car, carId);
        } else {
            return null;
        }
    }

    public Car deleteCar(Integer carId) {
        Car car = carDAO.delete(carId);
        if (car != null) {
            return car;
        } else {
            throw new CDNotFoundException("Car");
        }
    }

}
