package service;

import dao.GarageDAO;
import model.Garage;

import java.util.List;

public class GarageService {

    private GarageDAO garageDAO;

    //TODO: Solve logic without instantiation
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
