package application;

import com.google.inject.Inject;
import com.google.inject.Injector;

import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.ext.guice.RestletGuice;



public class CarServerComponent extends Component {

    public static void main(String[] args) throws Exception {
        Injector injector;
        injector = RestletGuice.createInjector(new InjectionModule());
        CarServerComponent component = injector.getInstance(CarServerComponent.class);
        component.start();
    }

    @Inject
    public CarServerComponent(CarServerApplication app) throws Exception {

        getServers().add(new Server(new Context(), Protocol.HTTP, 8080));
        getDefaultHost().attach(app);

    }
}
