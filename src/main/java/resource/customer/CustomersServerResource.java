package resource.customer;

import model.Customer;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import service.CustomerService;

import java.util.List;

public class CustomersServerResource extends ServerResource {

    //TODO: Solve logic without instantiation
    public static CustomerService customerService = new CustomerService();

    @Get
    public List getCustomers() {
        return customerService.getCustomers();
    }

    @Post
    public Customer saveCustomer(Customer customer) {
        return customerService.saveCustomer(customer);
    }
}
