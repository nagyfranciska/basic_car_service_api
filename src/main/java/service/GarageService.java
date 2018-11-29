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
        garageDAO.save(newGarage);
        return garageDAO.findById(newGarage.getId());
    }

    public Garage updateGarage(Garage garage) {
        garageDAO.update(garage);
        return garageDAO.findById(garage.getId());
    }

    public Garage deleteGarage(Garage garage) {
        garageDAO.delete(garage);
        return garage;
    }
}
