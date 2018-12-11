package di;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import dao.*;
import oauth.dao.ClientDAO;
import oauth.server.OauthApplication;
import oauth.service.ClientService;
import server.CarServerApplication;
import service.*;
import log.SLF4JTypeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InjectionModule extends AbstractModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(InjectionModule.class);

    @Override
    protected void configure() {

        bindListener(Matchers.any(), new SLF4JTypeListener());

        bind(CarServerApplication.class).asEagerSingleton();
        bind(OauthApplication.class).asEagerSingleton();

        bind(CustomerDAO.class).asEagerSingleton();
        bind(CarDAO.class).asEagerSingleton();
        bind(GarageDAO.class).asEagerSingleton();
        bind(ServiceDAO.class).asEagerSingleton();
        bind(UserDAO.class).asEagerSingleton();
        bind(ClientDAO.class).asEagerSingleton();

        bind(CustomerService.class).asEagerSingleton();
        bind(CarService.class).asEagerSingleton();
        bind(GarageService.class).asEagerSingleton();
        bind(ServiceService.class).asEagerSingleton();
        bind(UserService.class).asEagerSingleton();
        bind(ClientService.class).asEagerSingleton();

        bind(CarValidationService.class).asEagerSingleton();

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
