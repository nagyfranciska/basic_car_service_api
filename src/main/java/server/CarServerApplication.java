package server;

import com.google.inject.Inject;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Reference;
import org.restlet.ext.oauth.TokenVerifier;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.ext.guice.FinderFactory;
import org.restlet.routing.Router;
import resource.*;
import resource.customer.*;
import resource.customer.car.CarServerResource;
import resource.customer.car.CarsServerResource;
import resource.customer.car.service.ServicesForCarServerResource;
import resource.garage.GarageServerResource;
import resource.garage.GaragesServerResource;
import resource.garage.service.ServiceForGarageServerResource;
import resource.garage.service.ServicesForGarageServerResource;

public class CarServerApplication extends Application {

    public static String clientID, clientSecret;

    @Inject
    private FinderFactory finder;

    public CarServerApplication() {
        setName("Restlet Car Service");
    }

    public Restlet createInboundRoot() {

        Router router = new Router(getContext());

        router.attach("/status", StatusServerResource.class);

//        router.attach("/customers", finder.finder(CustomersServerResource.class));
//        router.attach("/customers/{customerId}", finder.finder(CustomerServerResource.class));
//
//        router.attach("/customers/{customerId}/users", finder.finder(UsersByCustomerServerResource.class));
//        router.attach("/customers/{customerId}/users/{userId}", finder.finder(UserServerResource.class));
//
//        router.attach("/customers/{customerId}/cars", finder.finder(CarsServerResource.class));
//        router.attach("/customers/{customerId}/cars/{carId}", finder.finder(CarServerResource.class));
//        router.attach("/customers/{customerId}/cars/{carId}/services", finder.finder(ServicesForCarServerResource.class));
//
//        router.attach("/garages", finder.finder(GaragesServerResource.class));
//        router.attach("/garages/{garageId}", finder.finder(GarageServerResource.class));
//
//        router.attach("/garages/{garageId}/users", finder.finder(UsersByGarageServerResource.class));
//        router.attach("/garages/{garageId}/users/{userId}", finder.finder(UserServerResource.class));
//
//        router.attach("/garages/{garageId}/services", finder.finder(ServicesForGarageServerResource.class));
//        router.attach("/garages/{garageId}/services/{serviceId}", finder.finder(ServiceForGarageServerResource.class));

        ChallengeAuthenticator bearerAuthenticator = new ChallengeAuthenticator(getContext(), ChallengeScheme.HTTP_OAUTH_BEARER, "Realm of Madness");
        bearerAuthenticator.setVerifier(new TokenVerifier(new Reference("riap://component/oauth/token_auth")));
        bearerAuthenticator.setNext(router);

    return bearerAuthenticator;


   }
}
