package resource.customer;

import com.google.inject.Inject;
import model.Customer;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import service.CustomerService;

import java.util.List;

public class CustomersServerResource extends ServerResource {

//    public CustomerService getThisShit() {
//
//        CustomerService customerService = injector.getInstance(CustomerService.class);
//        return customerService;
//    }

    @Inject
    CustomerService customerService;


    @Get
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
//        return getThisShit().getCustomers2();
    }

    @Post
    public Customer saveCustomer(Customer customer) {
        return customerService.saveCustomer(customer);
    }
}
