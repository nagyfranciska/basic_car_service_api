package resource.customer.car;

import model.Car;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import resource.customer.CustomersServerResource;
import service.CarService;
import service.CustomerService;

public class CarServerResource extends ServerResource {

    public CarService carService = CarsServerResource.carService;
    CustomerService customerService = CustomersServerResource.customerService;

//    @Get
//    public Car getCar() {
//        Integer id = Integer.parseInt(getAttribute("carId"));
//        return carService.getCarById(id);
//    }

    @Get
    public Car getCar() {
        Integer carId = Integer.parseInt(getAttribute("carId"));
        Integer customerId = Integer.parseInt(getAttribute("customerId"));
        return null;
    }

//    @Put
////    public Car updateCar(Car car) {
////        return carService.updateCar(car);
////    }

    @Put
    public Car updateCar(Car car) {
        Integer carId = car.getId();
        Integer customerId = Integer.parseInt(getAttribute("customerId"));
        carService.updateCar(car);
        return null;
    }

//    @Delete
//    public Car deleteCar(Car car) {
//        return carService.deleteCar(car);
//    }

    @Delete
    public Car deleteCar(Car car) {
        Integer carId = car.getId();
        Integer customerId = Integer.parseInt(getAttribute("customerId"));
        carService.deleteCar(car);
        return car;
    }

}