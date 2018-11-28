package service;

import dao.CarDAO;
import model.Car;

import java.time.LocalDate;
import java.util.Map;

public class CarService {



    public CarDAO carDAO;
    private Integer idCount = 0;

    public CarService() {
        carDAO = new CarDAO();
    }

    public Map<Integer, Car> getCars() {
        return carDAO.read();
    }

    public Car getCarById(Integer id) {
        if (carDAO.mapOfCars.containsKey(id)) {
            return carDAO.read(id);
        }
        return null;
    }

    public Car saveCar(Car newCar) {
        if (isValid(newCar)) {
            newCar.setId(idCount);
            idCount++;
            carDAO.create(newCar);
            return carDAO.read(newCar.getId());
        }
        return null;
    }

    public Car updateCar(Car car) {
        if (carDAO.mapOfCars.containsKey(car.getId())) {
            carDAO.update(car);
            return carDAO.read(car.getId());
        }
        return null;
    }

    public Car deleteCar(Car car) {
        if (carDAO.mapOfCars.containsKey(car.getId())) {
            return carDAO.delete(car);
        }
        return null;
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
        return carDAO.mapOfCars.values().stream()
                .anyMatch(c -> c.getPlate().equals(car.getPlate()));
    }
}