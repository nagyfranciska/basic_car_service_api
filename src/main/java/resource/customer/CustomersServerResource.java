package resource.customer;

import com.google.inject.Inject;
import model.Customer;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import service.CustomerService;

import java.util.List;

public class CustomersServerResource extends ServerResource {

    @Inject
    CustomerService customerService;

    @Get
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @Post
    public Customer saveCustomer(Customer customer) {
        return customerService.saveCustomer(customer);
    }
}
