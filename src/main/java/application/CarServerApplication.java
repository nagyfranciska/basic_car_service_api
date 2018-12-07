package application;

import com.google.inject.Inject;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.ext.guice.FinderFactory;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import resource.customer.*;
import resource.car.CarServerResource;
import resource.car.CarsServerResource;
import resource.service.ServicesByCarServerResource;
import resource.garage.GarageServerResource;
import resource.garage.GaragesServerResource;
import resource.service.ServiceServerResource;
import resource.service.ServicesByGarageServerResource;
import resource.user.UserServerResource;
import resource.user.UsersByCustomerServerResource;
import resource.user.UsersByGarageServerResource;
import service.authentication.BearerAuthService;

public class CarServerApplication extends Application {

    @Inject
    private FinderFactory finder;

    @Inject
    private BearerAuthService authService;

   public CarServerApplication() {
        setName("model.Car Server App No.1");
    }

    public Restlet createInboundRoot() {

        Router router = new Router(getContext());

        router.attach("/customers", finder.finder(CustomersServerResource.class));
        router.attach("/customers/{customerId}", finder.finder(CustomerServerResource.class));

        router.attach("/customers/{customerId}/cars", finder.finder(CarsServerResource.class));
        router.attach("/customers/{customerId}/cars/{carId}", finder.finder(CarServerResource.class));
        router.attach("/customers/{customerId}/cars/{carId}/services", finder.finder(ServicesByCarServerResource.class));

        router.attach("/garages", finder.finder(GaragesServerResource.class));
        router.attach("/garages/{garageId}", finder.finder(GarageServerResource.class));

        router.attach("/garages/{garageId}/services", finder.finder(ServicesByGarageServerResource.class));
        router.attach("/garages/{garageId}/services/{serviceId}", finder.finder(ServiceServerResource.class));

        router.attach("/garages/{garageId}/users", finder.finder(UsersByGarageServerResource.class));
        router.attach("/garages/{garageId}/users/{userId}", finder.finder(UserServerResource.class));

        router.attach("/customers/{customerId}/users", finder.finder(UsersByCustomerServerResource.class));
        router.attach("/customers/{customerId}/users/{userId}", finder.finder(UserServerResource.class));

        ChallengeAuthenticator authenticator = authService.getAuthenticator();
        authenticator.setNext(router);

        return authenticator;
    }

}
