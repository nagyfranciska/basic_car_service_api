import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import dao.CarDAO;
import dao.CustomerDAO;
import dao.GarageDAO;
import dao.ServiceDAO;
import log.SLF4JTypeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.CarService;
import service.CustomerService;
import service.GarageService;
import service.ServiceService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InjectionModule extends AbstractModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(InjectionModule.class);

    @Override
    protected void configure() {

        bindListener(Matchers.any(), new SLF4JTypeListener());

        bind(CarServerApplication.class).asEagerSingleton();

        bind(CustomerDAO.class).asEagerSingleton();
        bind(CarDAO.class).asEagerSingleton();
        bind(GarageDAO.class).asEagerSingleton();
        bind(ServiceDAO.class).asEagerSingleton();

        bind(CustomerService.class).asEagerSingleton();
        bind(CarService.class).asEagerSingleton();
        bind(GarageService.class).asEagerSingleton();
        bind(ServiceService.class).asEagerSingleton();

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
