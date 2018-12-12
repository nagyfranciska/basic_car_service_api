package resource.customer;

import com.google.inject.Inject;
import log.Log;
import model.Customer;
import model.User;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import service.CustomerService;

import java.util.List;

public class CustomersServerResource extends ServerResource {

    @Inject
    private CustomerService customerService;

    @Log
    Logger logger;

    @Get
    public List<Customer> getCustomers() {

        logger.info("Here we are");
        return customerService.getCustomers();
    }

    @Post
    public Customer saveCustomer(Customer customer) {
        return customerService.saveCustomer(customer);
    }
}
