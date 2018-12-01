package resource.customer.car;

import model.Car;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import service.CarService;
import java.util.List;

public class CarsServerResource extends ServerResource {

    static CarService carService = new CarService();

    @Get
    public List<Car> getCarsByCustomer() {
        return carService.getCarsByCustomer(Integer.parseInt(getAttribute("customerId")));
    }

    @Post
    public Car saveCar(Car car) {
        return carService.saveCar(car, Integer.parseInt(getAttribute("customerId")));
    }
}
