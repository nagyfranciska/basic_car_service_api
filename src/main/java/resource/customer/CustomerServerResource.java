package resource.customer;

import model.Customer;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import service.CustomerService;

public class CustomerServerResource extends ServerResource {

    private CustomerService customerService = CustomersServerResource.customerService;

    @Get
    public Customer getCustomer() {
        Integer id = Integer.parseInt(getAttribute("customerId"));
        return customerService.getCustomerById(id);
    }

    @Put
    public Customer updateCustomer(Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @Delete
    public Customer deleteCustomer(Customer customer) {
        return customerService.deleteCustomer(customer);
    }
}
