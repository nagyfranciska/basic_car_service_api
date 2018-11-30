package resource.customer.car;

import model.Car;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import service.CarService;

public class CarServerResource extends ServerResource {

    public CarService carService = CarsServerResource.carService;


    @Get
    public Car getCar() {
        Integer carId = Integer.parseInt(getAttribute("carId"));
        Integer customerId = Integer.parseInt(getAttribute("customerId"));
        return carService.getCarById(customerId, carId);
    }

    //TODO: Implement LinkedHashset in CarService
//    @Put
//    public Car updateCar(Car car) {
//        Integer customerId = Integer.parseInt(getAttribute("customerId"));
//        return carService.updateCar(customerId, car);
//    }

    @Delete
    public Car deleteCar(Car car) {
        Integer customerId = Integer.parseInt(getAttribute("customerId"));
        return carService.deleteCar(customerId, car);
    }
}