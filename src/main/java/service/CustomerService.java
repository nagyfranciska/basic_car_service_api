package service;

import dao.CustomerDAO;
import model.Customer;

import java.util.List;
import java.util.Map;

public class CustomerService {

    CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public List getCustomers() {
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
