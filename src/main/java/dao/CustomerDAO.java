package dao;

import model.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerDAO {

    public static Map<Integer, Customer> mapOfCustomers;

    public CustomerDAO() {
        mapOfCustomers = new HashMap<>();
    }

    public Map<Integer, Customer> read() {
        return mapOfCustomers;
    }

    public Customer read(Integer id) {
        return mapOfCustomers.get(id);
    }

    public Customer create(Customer customer) {
        return mapOfCustomers.put(customer.getId(), customer);
    }

    public Customer delete(Customer customer) {
        return mapOfCustomers.remove(customer.getId());
    }

    public Customer update(Customer customer) {
        return mapOfCustomers.put(customer.getId(), customer);
    }

}
