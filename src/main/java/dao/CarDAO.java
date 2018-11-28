package dao;

import model.Car;

import java.util.HashMap;
import java.util.Map;

public class CarDAO {

    public static Map<Integer, Car> mapOfCars;

    public CarDAO() {
    }



    public Map<Integer, Car> read() {
        return mapOfCars;
    }

    public Car read(Integer id) {
        return mapOfCars.get(id);
    }

    public Car create(Car car) {
        return mapOfCars.put(car.getId(), car);
    }

    public Car delete(Car car) {
        return mapOfCars.remove(car.getId());
    }

    public Car update(Car car) {
        return mapOfCars.put(car.getId(), car);
    }
}
