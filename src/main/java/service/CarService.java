package service;

import dao.CarDAO;
import model.Car;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CarService {

    private CarDAO carDAO;
    private CustomerService customerService;

    public CarService() {
        customerService = new CustomerService();
        carDAO = new CarDAO();
    }

    public List<Car> getCarsByCustomer(Integer customerId) {
        return carDAO.findAllByCustomer(customerId);
    }

    public Car getCarById(Integer carId) {
        return carDAO.findById(carId);
    }

    public Car saveCar(Car car, Integer customerId) {
        if (isValid(car)) {
            car.setCustomer(customerService.getCustomerById(customerId));
            carDAO.save(car);
            return getCarById(car.getId());
        }
        throw new ResourceException(Status.CLIENT_ERROR_NOT_ACCEPTABLE, "Car validation failed");
    }

    //TODO: Correct validation (without samePlate)
    public Car updateCar(Car car, Integer carId) {
        return carDAO.update(car, carId);
    }

    public Car deleteCar(Integer carId) {
        return carDAO.delete(carId);
    }

    private boolean isValid(Car car) {
        return (isNotJunk(car) && hasValidPlate(car) && !hasSamePlate(car));
    }

    private boolean isNotJunk(Car car) {
        return LocalDate.parse(car.getRegistrationDate()).isAfter(LocalDate.now().minusYears(10));
    }

    private boolean hasValidPlate(Car car) {
        return car.getPlate().matches("[A-Z]{3}-[0-9]{3}$");
    }

    //TODO: Refactor this to a query
    private boolean hasSamePlate(Car car) {
        List<Car> carList = new ArrayList<>();
        try {
            carList = carDAO.findAll();

        } catch (Exception e) {
            System.out.println("plate validation failed");
        }
        return carList.stream().anyMatch(c -> c.getPlate().equals(car.getPlate()));
    }
}