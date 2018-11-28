package dao;

import model.Garage;

import java.util.HashMap;
import java.util.Map;

public class GarageDAO {

    public static Map<Integer, Garage> mapOfGarages;

    public GarageDAO() {
        mapOfGarages = new HashMap<>();
    }

    public Map<Integer, Garage> read() {
        return mapOfGarages;
    }

    public Garage read(Integer id) {
        return mapOfGarages.get(id);
    }

    public Garage create(Garage garage) {
        return mapOfGarages.put(garage.getId(), garage);
    }

    public Garage delete(Garage garage) {
        return mapOfGarages.remove(garage.getId());
    }

    public Garage update(Garage garage) {
        return mapOfGarages.put(garage.getId(), garage);
    }


}
