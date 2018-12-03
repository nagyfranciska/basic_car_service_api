package server;

import com.google.inject.Inject;
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

   public CarServerApplication() {
        setName("model.Car Server App No.1");
    }

    public Restlet createInboundRoot() {

        Router router = new Router(getContext());

        router.attach("/customers", finder.finder(CustomersServerResource.class));
//        router.attach("/customers", CustomersServerResource.class);
        router.attach("/customers/{customerId}", finder.finder(CustomerServerResource.class));

        router.attach("/customers/{customerId}/cars", finder.finder(CarsServerResource.class));
        router.attach("/customers/{customerId}/cars/{carId}", finder.finder(CarServerResource.class));
        router.attach("/customers/{customerId}/cars/{carId}/services", finder.finder(ServicesForCarServerResource.class));

        router.attach("/garages", finder.finder(GaragesServerResource.class));
        router.attach("/garages/{garageId}", finder.finder(GarageServerResource.class));

        router.attach("/garages/{garageId}/services", finder.finder(ServicesForGarageServerResource.class));
        router.attach("/garages/{garageId}/services/{serviceId}", finder.finder(ServiceForGarageServerResource.class));

        return router;
    }

}
