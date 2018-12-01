
import com.google.inject.Inject;
import model.Customer;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.ext.guice.FinderFactory;
import org.restlet.routing.Router;
import resource.customer.*;
import resource.customer.car.CarServerResource;
import resource.customer.car.CarsServerResource;
import resource.customer.car.service.ServicesForCarServerResource;
import resource.garage.GarageServerResource;
import resource.garage.GaragesServerResource;
import resource.garage.service.ServiceForGarageServerResource;
import resource.garage.service.ServicesForGarageServerResource;

public class CarServerApplication extends Application {

    @Inject
    private FinderFactory finder;

    CarServerApplication() {
        setName("model.Car Server App No.1");
    }

    public Restlet createInboundRoot() {

        Router router = new Router(getContext());

        router.attach("/customers", finder.finder(CustomersServerResource.class));
        router.attach("/customers/{customerId}", CustomerServerResource.class);

        router.attach("/customers/{customerId}/cars", CarsServerResource.class);
        router.attach("/customers/{customerId}/cars/{carId}", CarServerResource.class);
        router.attach("/customers/{customerId}/cars/{carId}/services", ServicesForCarServerResource.class);

        router.attach("/garages", GaragesServerResource.class);
        router.attach("/garages/{garageId}", GarageServerResource.class);

        router.attach("/garages/{garageId}/services", ServicesForGarageServerResource.class);
        router.attach("/garages/{garageId}/services/{serviceId}", ServiceForGarageServerResource.class);

        return router;
    }

}
