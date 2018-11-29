package service;

import dao.GarageDAO;
import model.Garage;

import java.util.List;
import java.util.Map;

public class GarageService {

    GarageDAO garageDAO;

    public GarageService() {
        garageDAO = new GarageDAO();
    }

    public List getGarages() {
        return garageDAO.findAll();
    }

    public Garage getGarageById(Integer id) {
        return garageDAO.findById(id);
    }

    public Garage saveGarage(Garage newGarage) {
        return garageDAO.save(newGarage);
    }

    public Garage updateGarage(Garage garage) {
        return garageDAO.update(garage);
    }

    public Garage deleteGarage(Garage garage) {
        return garageDAO.delete(garage);

    }
}
