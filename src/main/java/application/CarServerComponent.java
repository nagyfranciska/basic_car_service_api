package application;

import com.google.inject.Inject;
import com.google.inject.Injector;

import oauth.ClientService;
import oauth.OAuthServerApplication;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.ext.guice.RestletGuice;
import org.restlet.ext.oauth.internal.Client;

public class CarServerComponent extends Component {

    public static void main(String[] args) throws Exception {
        Injector injector;
        injector = RestletGuice.createInjector(new InjectionModule());

        //TODO: Get Client from database
        Client client = injector.getInstance(ClientService.class).createClient(Client.ClientType.CONFIDENTIAL,
                new String[] {"http://localhost:8080/authorize"}, null);
        System.out.println("SampleClient: client_id=" + client.getClientId()
                + ", client_secret="
                + String.copyValueOf(client.getClientSecret()));

        CarServerComponent component = injector.getInstance(CarServerComponent.class);

        component.start();
    }

    @Inject
    public CarServerComponent(CarServerApplication app, OAuthServerApplication oauthApp) throws Exception {

        getServers().add(new Server(new Context(), Protocol.HTTP, 8080));
        getClients().add(Protocol.HTTP);
        getClients().add(Protocol.HTTPS);
        getClients().add(Protocol.RIAP);
        getClients().add(Protocol.CLAP);

        getDefaultHost().attach("/", app);
        getDefaultHost().attach("/oauth", oauthApp);
        getInternalRouter().attach("/oauth", app);

    }
}
