package server;

import com.google.inject.Inject;
import com.google.inject.Injector;

import di.InjectionModule;
import model.Garage;
import model.User;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.ext.guice.RestletGuice;
import org.restlet.ext.oauth.internal.Client;
import org.restlet.ext.oauth.internal.ClientManager;
import org.restlet.ext.oauth.internal.TokenManager;
import org.restlet.ext.oauth.internal.memory.MemoryClientManager;
import org.restlet.ext.oauth.internal.memory.MemoryTokenManager;
import service.GarageService;
import service.SampleUserManager;
import service.UserService;

import java.util.HashMap;
import java.util.Map;

import static org.restlet.ext.oauth.OAuthResourceDefs.REFRESH_TOKEN;

public class CarServerComponent extends Component {


    ///
//    http://localhost:8080/oauth/authorize?response_type=code&client_id=8bcfb431-9213-4143-98a1-407b9b1ded04&redirect_uri=http://localhost:8080/status&scope=read
    ///
    //TODO: create own Client, save it, add token to properties, change code to bearer_token
    // own Client (implement retlet User)) + userService (extends userAbstractUserManager)
    // add to Client's properties "token" (in client model, also set id)
    // hibernate cannot handle string array (red_uri) >> convert to String, and back? or find a way for it?

    private static SampleUserManager userManager;
    private static ClientManager clientManager;
    private static TokenManager tokenManager;

    public static SampleUserManager getSampleUserManager() {
        return userManager;
    }

    public static ClientManager getClientManager() {
        return clientManager;
    }

    public static TokenManager getTokenManager() {
        return tokenManager;
    }

    public static void main(String[] args) throws Exception {

        Injector injector;
        injector = RestletGuice.createInjector(new InjectionModule());
        CarServerComponent component = injector.getInstance(CarServerComponent.class);

        ////////////

        userManager = new SampleUserManager();
        userManager.addUser("user").setPassword("none".toCharArray());
        userManager.addUser("admin").setPassword("pwd".toCharArray());

        clientManager = new MemoryClientManager();
        Client client = (Client) clientManager.createClient(Client.ClientType.CONFIDENTIAL, new String[] {"http://localhost:8080/status"}, null);
        System.out.println("Client id: " + client.getClientId() + ", client secret: " + String.copyValueOf(client.getClientSecret()));

        CarServerApplication.clientID = client.getClientId();
        CarServerApplication.clientSecret = String.valueOf(client.getClientSecret());

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
