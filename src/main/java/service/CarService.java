package service;

import dao.CarDAO;
import model.Car;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarService {

    private CarDAO carDAO;

    //TODO: Solve logic without instantiation
    public CarService() {
        carDAO = new CarDAO();
    }

    public List getCars() {
        return carDAO.findAll();
    }

    public Car getCarById(Integer id) {
        return carDAO.findById(id);
    }

    public Car saveCar(Car newCar) {
        if (isValid(newCar)) {
            carDAO.save(newCar);
            return carDAO.findById(newCar.getId());
        }
        return null;
    }

    public Car updateCar(Car car) {
       carDAO.update(car);
       return carDAO.findById(car.getId());
    }

    public Car deleteCar(Car car) {
        carDAO.delete(car);
        return car;
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