package server;

import com.google.inject.Inject;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Reference;
import org.restlet.ext.guice.FinderFactory;
import org.restlet.ext.oauth.*;
import org.restlet.ext.oauth.internal.ClientManager;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MapVerifier;
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
        router.attach("/customers/{customerId}", finder.finder(CustomerServerResource.class));

        router.attach("/customers/{customerId}/cars", finder.finder(CarsServerResource.class));
        router.attach("/customers/{customerId}/cars/{carId}", finder.finder(CarServerResource.class));
        router.attach("/customers/{customerId}/cars/{carId}/services", finder.finder(ServicesForCarServerResource.class));

        router.attach("/garages", finder.finder(GaragesServerResource.class));
        router.attach("/garages/{garageId}", finder.finder(GarageServerResource.class));

        router.attach("/garages/{garageId}/services", finder.finder(ServicesForGarageServerResource.class));
        router.attach("/garages/{garageId}/services/{serviceId}", finder.finder(ServiceForGarageServerResource.class));

        //       >> Oauth2 Authorization <<

        // >> -- oauth server part -- <<

//        - authorization endpoint -
        router.attach(HttpOAuthHelper.getAuthPage(getContext()), AuthPageServerResource.class);
        HttpOAuthHelper.setAuthPageTemplate("authorize.html", getContext());
        HttpOAuthHelper.setAuthSkipApproved(true, getContext());
        HttpOAuthHelper.setErrorPageTemplate("error.html", getContext());
//        router.attach(HttpOAuthHelper.getLoginPage(getContext()), LoginPageServerResource.class);

//        - token endpoint -

        ChallengeAuthenticator clientAuth = new ChallengeAuthenticator(getContext(), ChallengeScheme.HTTP_OAUTH_BEARER, "Realm of Despair");
        ClientVerifier clientVerifier = new ClientVerifier(getContext());
        clientVerifier.setAcceptBodyMethod(true);
        clientAuth.setNext(AccessTokenServerResource.class);
        router.attach("/token", clientAuth);

//        - token auth for resource server -

        router.attach("token_auth", TokenAuthServerResource.class);

        final Directory resource = new Directory(getContext(), "clap://system/resource");

        router.attach("", resource);

        // >>  --  server part -- <<

        ChallengeAuthenticator bearerAuth = new ChallengeAuthenticator(getContext(), ChallengeScheme.HTTP_OAUTH_BEARER, "Real of Madness");
        bearerAuth.setVerifier(new TokenVerifier(new Reference("riap://component/oauth/token_auth")));
        bearerAuth.setNext(router);

        return bearerAuth;
    }

}
