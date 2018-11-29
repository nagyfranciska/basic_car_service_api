package service;

import dao.CustomerDAO;
import model.Customer;

import java.util.List;

public class CustomerService {

    private CustomerDAO customerDAO;

    //TODO: Solve logic without instantiation
    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public List<Customer> getCustomers() {
        return customerDAO.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customerDAO.findById(id);
    }

    public Customer saveCustomer(Customer customer) {
        customerDAO.save(customer);
        return customerDAO.findById(customer.getId());
    }

    public Customer updateCustomer(Customer customer) {
        customerDAO.update(customer);
        return customerDAO.findById(customer.getId());
    }

    public Customer deleteCustomer(Customer customer) {
        customerDAO.delete(customer);
        return customer;
    }
}
