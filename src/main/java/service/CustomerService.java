package service;

import com.google.inject.Inject;
import dao.CustomerDAO;
import exception.general.CDNotFoundException;
import model.Customer;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

import java.util.List;

public class CustomerService {

    @Inject
    private CustomerDAO customerDAO;

    public CustomerService() {
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = customerDAO.findAll();
        if (customers.isEmpty()) {
            Response.getCurrent().setStatus(Status.SUCCESS_NO_CONTENT);
            return null;
        } else {
            return customers;
        }
    }

    public Customer saveCustomer(Customer customer) {
        Response.getCurrent().setStatus(Status.SUCCESS_CREATED);
        return customerDAO.save(customer);
    }

    public Customer getCustomerById(Integer customerId) {
        Customer customer = customerDAO.findById(customerId);
        if (customer != null) {
            return customer;
        } else {
            throw new CDNotFoundException("Customer");
        }
    }

    public Customer updateCustomer(Customer customer, Integer customerId) {
        return customerDAO.update(customer, customerId);
    }

    public Customer deleteCustomer(Integer customerId) {
        Customer customer = customerDAO.delete(customerId);
        if (customer != null) {
            return customer;
        } else {
            throw new CDNotFoundException("Customer");
        }
    }

}
