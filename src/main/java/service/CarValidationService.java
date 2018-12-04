package service;

import com.google.inject.Inject;
import dao.CarDAO;
import exception.validation.PlateExistsException;
import exception.validation.PlateFormatException;
import exception.validation.RegDateInvalidException;
import model.Car;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CarValidationService {

    @Inject
    private CarDAO carDAO;

    @Inject
    private CustomerService customerService;

    boolean carIsValid(Car car) {
        return (hasValidRegDate(car.getRegistrationDate()) && hasValidPlate(car.getPlate()) && hasUniquePlate(car.getPlate()));
    }

    boolean carUpdateIsValid(Car car) {
        return (hasValidRegDate(car.getRegistrationDate()) && hasValidPlate(car.getPlate()));
    }

    private boolean hasValidRegDate(String regDate) throws DateTimeParseException {
        LocalDate carRegDate = LocalDate.parse(regDate);
        if (carRegDate.isAfter(LocalDate.now().minusYears(10)) && carRegDate.isBefore(LocalDate.now())) {
            return true;
        } else {
            throw new RegDateInvalidException();
        }
    }

    private boolean hasValidPlate(String plate) {
        if (plate.matches("[A-Z]{3}-[0-9]{3}$")) {
            return true;
        } else {
            throw new PlateFormatException();
        }
    }

    private boolean hasUniquePlate(String plate) {
        if (carDAO.plateIsUnique(plate)) {
            return true;
        } else {
            throw new PlateExistsException();
        }
    }

}
