package resource.customer.car;

import com.google.inject.Inject;
import model.Car;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import service.CarService;

public class CarServerResource extends ServerResource {

    @Inject
    CarService carService;


    @Get
    public Car getCar() {
        return carService.getCarById(Integer.parseInt(getAttribute("carId")));
    }

    @Put
    public Car updateCar(Car car) {
        return carService.updateCar(car, Integer.parseInt(getAttribute("carId")));
    }

    @Delete
    public Car deleteCar() {
        return carService.deleteCar(Integer.parseInt(getAttribute("carId")));
    }
}