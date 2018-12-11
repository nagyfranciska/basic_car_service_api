package server;

import com.google.inject.Inject;
import com.google.inject.Injector;

import di.InjectionModule;
import oauth.model.Client;
import oauth.server.OauthApplication;
import oauth.service.ClientService;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.ext.guice.RestletGuice;
import org.restlet.ext.oauth.ResponseType;
import org.restlet.ext.oauth.internal.TokenManager;
import org.restlet.ext.oauth.internal.memory.MemoryTokenManager;
import service.SampleUserManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class CarServerComponent extends Component {


    ///
//    http://localhost:8080/oauth/authorize?response_type=code&client_id=7685b684-3035-4cf9-b859-27b5bba49eeb&redirect_uri=http://localhost:8080/status&scope=read
    //

    private static SampleUserManager userManager;
    private static TokenManager tokenManager;

    public static SampleUserManager getSampleUserManager() {
        return userManager;
    }

    public static TokenManager getTokenManager() {
        return tokenManager;
    }


    public static void main(String[] args) throws Exception {

        Injector injector;
        injector = RestletGuice.createInjector(new InjectionModule());

        ///////// >> create two Clients, one without properties, one with it
        ///////// >> expected result: both of them is created, first one with 'token', second one with 'token' and 'code'

//        String[] URIs = {"http://localhost:8080/status"};
//        Client client = (Client) injector.getInstance(ClientService.class).createClient(org.restlet.ext.oauth.internal.Client.ClientType.PUBLIC, URIs, null);
//
//        Map<String, Object> map = new HashMap<>() {{put(org.restlet.ext.oauth.internal.Client.PROPERTY_SUPPORTED_FLOWS, ResponseType.code);}};
//        map.put(org.restlet.ext.oauth.internal.Client.PROPERTY_SUPPORTED_FLOWS, ResponseType.token);
//
//        Client client2 = (Client) injector.getInstance(ClientService.class).createClient(org.restlet.ext.oauth.internal.Client.ClientType.PUBLIC, URIs, map);
//
//        ////////// >> get created Client, first is the null prop, second is given prop
//
////        Client client = injector.getInstance(ClientService.class).findById("");
//
//        System.out.println("client id: " + client.getClientId() + ",  secret: " + Arrays.toString(client.getClientSecret()));
//        System.out.println(client.getProperties().get("supported_flow"));
//        System.out.println("client id: " + client2.getClientId() + ",  secret: " + Arrays.toString(client2.getClientSecret()));
//        System.out.println(client2.getProperties());

        CarServerComponent component = injector.getInstance(CarServerComponent.class);

        ////////////

        userManager = new SampleUserManager();
        userManager.addUser("user").setPassword("none".toCharArray());
        userManager.addUser("admin").setPassword("pwd".toCharArray());

        tokenManager = new MemoryTokenManager();

        //////////////

        component.start();
    }

    @Inject
    public CarServerComponent(CarServerApplication app, OauthApplication oauthApp) throws Exception {

        getClients().add(Protocol.HTTP);
        getClients().add(Protocol.HTTPS);
        getClients().add(Protocol.RIAP);
        getClients().add(Protocol.CLAP);
        getServers().add(new Server(new Context(), Protocol.HTTP, 8080));

        getDefaultHost().attach("/", app);
        getDefaultHost().attach("/oauth", oauthApp);
        getInternalRouter().attach("/oauth", oauthApp);

    }
}
