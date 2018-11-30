package service;

import dao.CarDAO;
import model.Car;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CarService {

    private CarDAO carDAO;
    private CustomerService customerService;

    //TODO: Solve logic without instantiation
    public CarService() {
        carDAO = new CarDAO();
    }

    public List<Car> getCarsByCustomer(Integer customerId) {
        List rawList = carDAO.findAllByCustomer(customerId);
        List<Car> carList = new CopyOnWriteArrayList<>();
        try {
            rawList.forEach(c -> carList.add((Car)c));
        } catch (TypeNotPresentException e) {
            System.out.println("error in CarService with carList");
        }
        return carList;
    }

    public Car getCarById(Integer customerId, Integer carId) {
        Car car = (Car)carDAO.findById(customerId, carId);
        return car;
    }

    public Car saveCar(Car car, Integer customerId) {
        if (isValid(car)) {
            car.setCustomer(customerService.getCustomerById(customerId));
            carDAO.save(car);
            return getCarById(customerId, car.getId());
        }
        return null;
    }

    //TODO: Implement update() method on LinkedHashSet
//    public Car updateCar(Integer customerId, Car car) {
//
//        carDAO.update(car);
//        customerService.getCustomerById(customerId).getCarList().set(car.getId(), car);
//        Car updatedCar = (Car)carDAO.findById(customerId, car.getId());
//        return updatedCar;
//    }

    public Car deleteCar(Integer customerId, Car car) {
        Car deletedCar = (Car)carDAO.findById(customerId, car.getId());
        int carId = car.getId();
        customerService.getCustomerById(customerId).getCarList().remove(carId);
        carDAO.delete(car);
        return deletedCar;
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