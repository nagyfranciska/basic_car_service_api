package resource.customer.car;

import com.google.inject.Inject;
import model.Car;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import service.CarService;
import java.util.List;

public class CarsServerResource extends ServerResource {

    @Inject
    private CarService carService;

    @Get
    public List<Car> getCarsByCustomer() {
        return carService.getCarsByCustomer(Integer.parseInt(getAttribute("customerId")));
    }

    @Post
    public Car saveCar(Car car) {
        return carService.saveCar(car, Integer.parseInt(getAttribute("customerId")));
    }
}
