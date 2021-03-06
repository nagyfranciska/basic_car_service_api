package resource.customer;

import com.google.inject.Inject;
import model.Customer;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import service.model.CustomerService;

public class CustomerServerResource extends ServerResource {

    @Inject
    private CustomerService customerService;

    @Get
    public Customer getCustomer() {
        return customerService.getCustomerById(Integer.parseInt(getAttribute("customerId")));
    }

    @Put
    public Customer updateCustomer(Customer customer) {
        return customerService.updateCustomer(customer, Integer.parseInt(getAttribute("customerId")));
    }

    @Delete
    public Customer deleteCustomer() {
        return customerService.deleteCustomer(Integer.parseInt(getAttribute("customerId")));
    }
}
