package service;

import dao.CustomerDAO;
import model.Customer;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CustomerService {

    private CustomerDAO customerDAO;

    //TODO: Solve logic without instantiation
    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public List<Customer> getCustomers() {
        List rawList = customerDAO.findAll();
        List<Customer> customerList = new CopyOnWriteArrayList<>();
        try {
            rawList.forEach(c -> customerList.add((Customer)c));
        } catch (TypeNotPresentException e) {
            System.out.println("error in CustomerService with customerList");
        }
        return customerList;
    }

    public Customer getCustomerById(Integer id) {
        return customerDAO.findById(id);
    }

    public Customer saveCustomer(Customer customer) {
        customerDAO.save(customer);
        return getCustomerById(customer.getId());
    }

    public Customer updateCustomer(Customer customer) {
        customerDAO.update(customer);
        return getCustomerById(customer.getId());
    }

    public Customer deleteCustomer(Customer customer) {
        Customer deletedCustomer = getCustomerById(customer.getId());
        customerDAO.delete(customer);
        return deletedCustomer;
    }
}
