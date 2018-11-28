package service;

import dao.GarageDAO;
import model.Garage;

import java.util.Map;

public class GarageService {

    public GarageDAO garageDAO;

    public GarageService() {
        garageDAO = new GarageDAO();
    }

    public Map<Integer, Garage> getGarages() {
        return garageDAO.mapOfGarages;
    }

    public Garage getGarageById(Integer id) {
        return garageDAO.read(id);
    }

    public Garage saveGarage(Garage newGarage) {
        return garageDAO.create(newGarage);
    }

    public Garage updateGarage(Garage garage) {
        return garageDAO.update(garage);
    }

    public Garage deleteGarage(Garage garage) {
        return garageDAO.delete(garage);
    }
}
