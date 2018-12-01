package service;

import com.google.inject.Inject;
import dao.CarDAO;
import model.Car;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CarValidationService {

    @Inject
    private CarDAO carDAO;

    boolean carIsValid(Car car) {
        return (isNotTooOld(car) && hasValidPlate(car) && !hasSamePlate(car));
    }

    boolean carUpdateIsValid(Car car) {
        return (isNotTooOld(car) && hasValidPlate(car));
    }

    private boolean isNotTooOld(Car car) throws DateTimeParseException {
        LocalDate carRegDate = LocalDate.parse(car.getRegistrationDate());

        if (carRegDate.isAfter(LocalDate.now().minusYears(10))) {
            return true;

        } else {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,
                    "Server only accepts Cars registered after: " + LocalDate.now());
        }

    }

    private boolean hasValidPlate(Car car) {

        if (car.getPlate().matches("[A-Z]{3}-[0-9]{3}$")) {
            return true;

        } else {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,
                    "Car Plate format is invalid.\nPlease use the following format: \"ABC-123\"");
        }

    }

    private boolean hasSamePlate(Car car) {

        if (carDAO.plateExists(car.getPlate())) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,
                    "Car with this plate already exists");

        } else {
            return true;
        }

    }

}
