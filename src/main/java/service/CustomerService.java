package service;

import dao.CustomerDAO;
import model.Customer;

import java.util.List;

public class CustomerService {

    private CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public List<Customer> getCustomers() {
        return customerDAO.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        customerDAO.save(customer);
        return getCustomerById(customer.getId());
    }

    public Customer getCustomerById(Integer id) {
        return customerDAO.findById(id);
    }

    public Customer updateCustomer(Customer customer, Integer customerId) {
        return customerDAO.update(customer, customerId);
    }

    public Customer deleteCustomer(Integer customerId) {
        return customerDAO.delete(customerId);
    }
}
