import com.fasterxml.jackson.annotation.JacksonInject;
import dao.CarDAO;
import model.*;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import resource.customer.*;
import resource.customer.car.CarServerResource;
import resource.customer.car.CarsServerResource;
import resource.customer.car.service.ServicesForCarServerResource;
import resource.garage.GarageServerResource;
import resource.garage.GaragesServerResource;
import resource.garage.service.ServiceForGarageServerResource;
import resource.garage.service.ServicesForGarageServerResource;
import service.CarService;
import service.CustomerService;
import service.GarageService;
import service.ServiceService;

public class CarServerApplication extends Application {

    public CarServerApplication() {
        setName("model.Car Server App No.1");
    }

    public Restlet createInboundRoot() {

        Router router = new Router(getContext());

        router.attach("/customers", CustomersServerResource.class);
        router.attach("/customers/{customerId}", CustomerServerResource.class);
        router.attach("/customers/{customerId}/cars", CarsServerResource.class);
        router.attach("/customers/{customerId}/cars/{carId}", CarServerResource.class);
        router.attach("/customers/{customerId}/cars/{carId}/services", ServicesForCarServerResource.class);
        router.attach("/garages", GaragesServerResource.class);
        router.attach("/garages/{garageId}", GarageServerResource.class);
        router.attach("/garages/{garageId}/services", ServicesForGarageServerResource.class);
        router.attach("/garages/{garageId}/services/{serviceId}", ServiceForGarageServerResource.class);

        return router;
    }

//    public void init() {
//
//        Car car01 = new Car(CarType.TOYOTA, "AAA-000", "2015-01-01", 5, 5, "black");
//        car01.setId(0);
//        Car car02 = new Car(CarType.FORD, "BBB-111", "2016-01-01", 4, 2, "white");
//        car02.setId(1);
//        Car car03 = new Car(CarType.BMW, "CCC-222", "2017-01-01", 5, 4, "red");
//        car03.setId(2);
//
//        Customer customer01 = new Customer("John Doe", "Address str 05", "same", "type");
//        customer01.setId(0);
//        Customer customer02 = new Customer("Jane Doe", "Dummy str 666", "same", "type");
//        customer02.setId(1);
//        Customer customer03 = new Customer("Mr. Nobody", "NoName street", "same", "type");
//        customer03.setId(3);
//
//        Garage garage = new Garage("No Lost Case", "Garage str 02", 7);
//        garage.setId(0);
//
//        Service service01 = new Service("startDate", "endDate", 500);
//        service01.setId(0);
//        Service service02 = new Service("startDate2", "endDate2", 800);
//        service02.setId(1);
//
//        //////////////////////////////
//
//        customer01.getCarList().add(car01);
//        customer01.getCarList().add(car02);
//
//        customer02.getCarList().add(car03);
//
//        customer01.getServiceList().add(service01);
//        customer01.getServiceList().add(service02);
//
//        customer02.getServiceList().add(service02);
//
//        garage.getServiceList().add(service01);
//        garage.getServiceList().add(service02);
//
//        ///////////////////////////////
//
//        CarDAO.mapOfCars.put(car01.getId(), car01);
//
//    }
}
