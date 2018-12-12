package server;

import com.google.inject.Inject;
import com.google.inject.Injector;

import di.InjectionModule;
import oauth.server.OauthApplication;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.ext.guice.RestletGuice;
import org.restlet.ext.oauth.internal.TokenManager;
import org.restlet.ext.oauth.internal.memory.MemoryTokenManager;

public class CarServerComponent extends Component {

    ///
//    http://localhost:8080/oauth/authorize?response_type=token&client_id=ZZkYn&redirect_uri=http://localhost:9090&scope=read
//    after this, postman works correctly with given token!
    //

    private static TokenManager tokenManager;

    public static TokenManager getTokenManager() {
        return tokenManager;
    }

    public static void main(String[] args) throws Exception {

        Injector injector;
        injector = RestletGuice.createInjector(new InjectionModule());

        ///////// >> create two Clients, one without properties, one with it
        ///////// >> expected result: both of them is created, first one with 'token', second one with 'token' and 'code'

//        String[] URIs = {"http://localhost:8080/dummy"};
//        Map<String, Object> map = new HashMap<>();
//        map.put(org.restlet.ext.oauth.internal.Client.PROPERTY_SUPPORTED_FLOWS, ResponseType.code);
//
//        Client client1 = (Client) injector.getInstance(ClientService.class).createClient(org.restlet.ext.oauth.internal.Client.ClientType.CONFIDENTIAL, URIs, map);
//        Client client2 = (Client) injector.getInstance(ClientService.class).createClient(org.restlet.ext.oauth.internal.Client.ClientType.CONFIDENTIAL, URIs, null);
//        Client client3 = (Client) injector.getInstance(ClientService.class).createClient(org.restlet.ext.oauth.internal.Client.ClientType.PUBLIC, URIs, map);
//        Client client4 = (Client) injector.getInstance(ClientService.class).createClient(org.restlet.ext.oauth.internal.Client.ClientType.PUBLIC, URIs, null);
//
//        System.out.println("client id: " + client1.getClientId() + ",  secret: " + client1.getClientSecretAsString() + ", props (should be code): " + client1.getPropertiesAsString());
//        System.out.println("client id: " + client2.getClientId() + ",  secret: " + client2.getClientSecretAsString() + ", props (should be code): " + client2.getPropertiesAsString());
//        System.out.println("client id: " + client3.getClientId() + ",  secret: " + client3.getClientSecretAsString() + ", props (should be code): " + client3.getPropertiesAsString());
//        System.out.println("client id: " + client4.getClientId() + ",  secret: " + client4.getClientSecretAsString() + ", props (should be token): " + client4.getPropertiesAsString());

        ////////////

        tokenManager = new MemoryTokenManager();

        //////////////

        CarServerComponent component = injector.getInstance(CarServerComponent.class);
        component.start();
    }

    @Inject
    public CarServerComponent(CarServerApplication app, OauthApplication oauthApp) throws Exception {

        getClients().add(Protocol.HTTP);
        getClients().add(Protocol.HTTPS);
        getClients().add(Protocol.RIAP);
        getClients().add(Protocol.CLAP);
        getServers().add(new Server(new Context(), Protocol.HTTP, 8080));

        getDefaultHost().attach("/api", app);
        getDefaultHost().attach("/oauth", oauthApp);
        getInternalRouter().attach("/oauth", oauthApp);

    }
}
