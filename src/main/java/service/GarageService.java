package service;

import com.google.inject.Inject;
import dao.GarageDAO;
import exception.general.CDNotFoundException;
import model.Garage;
import org.restlet.Response;
import org.restlet.data.Status;

import java.util.List;

public class GarageService {

    @Inject
    private GarageDAO garageDAO;

    public GarageService() {
    }

    public List<Garage> getGarages() {
        List<Garage> garages = garageDAO.findAll();
        if (garages.isEmpty()) {
            Response.getCurrent().setStatus(Status.SUCCESS_NO_CONTENT);
            return null;
        } else {
            return garages;
        }
    }

    public Garage saveGarage(Garage garage) {
        Response.getCurrent().setStatus(Status.SUCCESS_CREATED);
        return garageDAO.save(garage);
    }

    public Garage getGarageById(Integer garageId) {
        Garage garage = garageDAO.findById(garageId);
        if (garage != null) {
            return garage;
        } else {
            throw new CDNotFoundException("Garage");
        }
    }

    public Garage updateGarage(Garage garage, Integer garageId) {
        return garageDAO.update(garage, garageId);
    }

    public Garage deleteGarage(Integer garageId) {
        Garage garage = garageDAO.delete(garageId);
        if (garage != null) {
            return garage;
        } else {
            throw new CDNotFoundException("Garage");
        }
    }

}
