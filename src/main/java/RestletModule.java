import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import dao.CustomerDAO;
import service.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RestletModule extends AbstractModule {

//    private static final Logger LOGGER = LoggerFactory.getLogger(RestletModule.class);

    @Override
    protected void configure() {

//        JPAUtility.initJPA();

        bind(CustomerDAO.class).asEagerSingleton();
//        bind(CarDAO.class).asEagerSingleton();
//        bind(GarageDAO.class).asEagerSingleton();
//        bind(ServiceDAO.class).asEagerSingleton();

        bind(CustomerService.class).asEagerSingleton();
//        bind(CarService.class).asEagerSingleton();
//        bind(GarageService.class).asEagerSingleton();
//        bind(ServiceService.class).asEagerSingleton();
        bind(CarServerApplication.class).asEagerSingleton();
//        bindInterceptor(Matchers.subclassesOf(CustomerService.class), Matchers.any());

//        bind(CustomersServerResource.class).asEagerSingleton();



    }

    @Provides
    @Singleton
    public EntityManagerFactory providesManagerFactory() {
        try {
            return Persistence.createEntityManagerFactory("jpa");
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

