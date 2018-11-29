import org.restlet.Component;
import org.restlet.data.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.JPAUtility;

public class CarServerComponent extends Component {

    private static final Logger logger = LoggerFactory.getLogger(CarServerComponent.class);

    public static void main(String[] args) throws Exception {

        new CarServerComponent().start();

        logger.trace("This is a trace from {}", CarServerComponent.class.getSimpleName());
        logger.debug("This is a debug from {}", CarServerComponent.class.getSimpleName());
        logger.info("This is an info from {}", CarServerComponent.class.getSimpleName());
        logger.warn("This is a warn from {}", CarServerComponent.class.getSimpleName());
        logger.error("This is an error from {}", CarServerComponent.class.getSimpleName());

    }

    public CarServerComponent() throws Exception {

        JPAUtility.initJPA();
        CarServerApplication app = new CarServerApplication();
        getServers().add(Protocol.HTTP, 8080);
        getDefaultHost().attachDefault(app);
        JPAUtility.initJPA();

    }
}
