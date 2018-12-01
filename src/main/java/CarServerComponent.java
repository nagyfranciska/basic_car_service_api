import com.google.inject.Inject;
import com.google.inject.Injector;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.ext.guice.RestletGuice;
import service.JPAUtility;

public class CarServerComponent extends Component {

    private static Injector injector;

    public static void main(String[] args) throws Exception {
        injector = RestletGuice.createInjector(new RestletModule());

        CarServerComponent csc = injector.getInstance(CarServerComponent.class);
        csc.start();

    }

    @Inject
    public CarServerComponent(CarServerApplication app) throws Exception {

        getServers().add(new Server(new Context(), Protocol.HTTP, 8080));
        getDefaultHost().attach(app);

    }
}
