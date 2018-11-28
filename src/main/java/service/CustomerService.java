package service;

import dao.CustomerDAO;
import model.Customer;

import java.util.Map;

public class CustomerService {

    public CustomerDAO customerDAO;
    private Integer idCount = 0;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public Map<Integer, Customer> getCustomers() {
        return customerDAO.read();
    }

    public Customer getCustomerById(Integer id) {
        return customerDAO.read(id);
    }

    public Customer saveCustomer(Customer customer) {
        customer.setId(idCount);
        idCount++;
        return customerDAO.create(customer);
    }

    public Customer updateCustomer(Customer customer) {
        return customerDAO.update(customer);
    }

    public Customer deleteCustomer(Customer customer) {
        return customerDAO.delete(customer);
    }
}
