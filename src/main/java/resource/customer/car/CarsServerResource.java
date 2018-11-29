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
        Integer customerId = Integer.parseInt(getAttribute("customerId"));
        return carService.getCarsByCustomer(customerId);
    }

    @Post
    public Car saveCar(Car car) {
        Integer customerId = Integer.parseInt(getAttribute("customerId"));
        return carService.saveCar(car, customerId);
    }
}
