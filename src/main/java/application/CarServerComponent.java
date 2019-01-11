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

        Client client = injector.getInstance(ClientService.class).findById("71d32dae-d32e-406d-a188-b255fae2f752");
        System.out.println("CLIENT: client_id=" + client.getClientId()
                + ", client_secret="
                + String.copyValueOf(client.getClientSecret()) + ", client_properties=" + client.getProperties().toString());

        CarServerComponent component = injector.getInstance(CarServerComponent.class);

        component.start();
    }

    @Inject
    public CarServerComponent(CarServerApplication app, OAuthServerApplication oauthApp) {

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
