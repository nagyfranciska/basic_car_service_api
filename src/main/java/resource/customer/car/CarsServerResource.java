package resource.customer.car;

import model.Car;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import resource.customer.CustomersServerResource;
import service.CarService;
import service.CustomerService;

import java.util.List;
import java.util.Map;

public class CarsServerResource extends ServerResource {

    public static CarService carService = new CarService();
    CustomerService customerService = CustomersServerResource.customerService;

//    @Get
//    public Map<Integer, Car> getCars() {
//        return carService.getCars();
//    }

    @Get
    public List<Car> getCars() {
        Integer customerId = Integer.parseInt(getAttribute("customerId"));
        return null;
    }

//    @Post
//    public Car saveCar(Car car) {
//        return carService.saveCar(car);
//    }

    @Post
    public Car saveCar(Car car) {
        Integer customerId = Integer.parseInt(getAttribute("customerId"));
        carService.saveCar(car);
        return car;
    }
}
