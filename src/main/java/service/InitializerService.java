//package service;
//
//import com.google.inject.Inject;
//import dao.CarDAO;
//import dao.CustomerDAO;
//import dao.GarageDAO;
//import dao.ServiceDAO;
//import model.*;
//
//public class InitializerService {
//
//    public static void main(String args[]) {
////        JPAUtility.initJPA();
//
//        CustomerDAO customerDAO = new CustomerDAO();
//        GarageDAO garageDAO = new GarageDAO();
//        CarDAO carDAO = new CarDAO();
//        ServiceDAO serviceDAO = new ServiceDAO();
//
//        Customer customerA = new Customer("Arthur Weasley", "13, Odú utca, Widra St. Capdel",
//                "13, Odú utca, Widra St. Capdel", "free");
//        Customer customerB = new Customer("Albert Runcorn", "128/B, Lettingham utca, London",
//                "128/B, Lettingham utca, London", "premium");
//
//        Garage garageA = new Garage("Szerelde", "43, Abszol út, London", 30);
//        Garage garageB = new Garage("Foltos és Bozont", "95, Abszol út, London", 60);
//
//        Car carA = new Car(CarType.FORD, "ZTS-827", "2010-05-03", 5, 3, "blue");
//        Car carB = new Car(CarType.AUDI, "CAR-103", "2014-08-10", 5, 5, "black");
//        Car carC = new Car(CarType.HONDA, "TOR-876", "2009-08-12", 8, 5, "grey");
//
//        Service serviceA = new Service("2018-08-10", "2018-09-03", 30000);
//        Service serviceB = new Service("2018-08-15", "2018-09-18", 65000);
//        Service serviceC = new Service("2018-10-08", "2018-11-06", 110000);
//
//        //Saving Garage first to avoid detached state persistence
//        garageDAO.save(garageA);
//        garageDAO.save(garageB);
//
//        //Creating connections between objects
//        garageA.getServiceList().add(serviceA);
//        garageB.getServiceList().add(serviceB);
//        garageB.getServiceList().add(serviceC);
//
//        serviceA.setCustomer(customerA);
//        serviceA.setCar(carA);
//        serviceB.setCustomer(customerA);
//        serviceB.setCar(carA);
//        serviceC.setCustomer(customerB);
//        serviceC.setCar(carB);
//        serviceA.setGarage(garageA);
//        serviceB.setGarage(garageB);
//        serviceC.setGarage(garageB);
//
//        carA.getServiceList().add(serviceA);
//        carA.getServiceList().add(serviceB);
//        carA.setCustomer(customerA);
//        carB.getServiceList().add(serviceC);
//        carB.setCustomer(customerB);
//        carC.setCustomer(customerB);
//
//        customerA.getCarList().add(carA);
//        customerA.getServiceList().add(serviceA);
//        customerA.getServiceList().add(serviceB);
//        customerB.getCarList().add(carB);
//        customerB.getCarList().add(carC);
//        customerB.getServiceList().add(serviceC);
//
//        //Persisting Costumer, this cascades down to Car and Service
//        customerDAO.save(customerA);
//        customerDAO.save(customerB);
//
//        System.out.println("DATABASE FILLED");
//    }
//
//}
