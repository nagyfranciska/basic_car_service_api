package service;

import dao.GarageDAO;
import model.Garage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GarageService {

    private GarageDAO garageDAO;

    //TODO: Solve logic without instantiation
    public GarageService() {
        garageDAO = new GarageDAO();
    }

    public List<Garage> getGarages() {
        List rawList = garageDAO.findAll();
        List<Garage> garageList = new CopyOnWriteArrayList<>();
        try {
            rawList.forEach(g -> garageList.add((Garage) g));
        } catch (TypeNotPresentException e) {
            System.out.println("error in GarageService with garageList");
        }
        return garageList;
    }

    public Garage getGarageById(Integer id) {
        return garageDAO.findById(id);
    }

    public Garage saveGarage(Garage garage) {
        garageDAO.save(garage);
        return getGarageById(garage.getId());
    }

    public Garage updateGarage(Garage garage) {
        garageDAO.update(garage);
        return getGarageById(garage.getId());
    }

    public Garage deleteGarage(Garage garage) {
        Garage deletedGarage = getGarageById(garage.getId());
        garageDAO.delete(garage);
        return deletedGarage;
    }
}
