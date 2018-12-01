import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.restlet.Component;
import org.restlet.data.Protocol;

public class CarServerComponent extends Component {

//    private static final Logger logger = LoggerFactory.getLogger(CarServerComponent.class);
    private static Injector injector;

    public static void main(String[] args) throws Exception {

        injector = Guice.createInjector(new InjectionModule());
        CarServerComponent component = injector.getInstance(CarServerComponent.class);
        component.start();

//        logger.trace("This is a trace from {}", CarServerComponent.class.getSimpleName());
//        logger.debug("This is a debug from {}", CarServerComponent.class.getSimpleName());
//        logger.info("This is an info from {}", CarServerComponent.class.getSimpleName());
//        logger.warn("This is a warn from {}", CarServerComponent.class.getSimpleName());
//        logger.error("This is an error from {}", CarServerComponent.class.getSimpleName());

    }

    @Inject
    public CarServerComponent(CarServerApplication app) throws Exception {

        getServers().add(Protocol.HTTP, 8080);
        getDefaultHost().attachDefault(app);
//        JPAUtility.initJPA();

    }
}
