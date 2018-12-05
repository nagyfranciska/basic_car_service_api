package resource;

import com.google.inject.Inject;
import model.CDUser;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import service.UserService;

import java.util.List;

public class UsersByCustomerServerResource extends ServerResource {

    @Inject
    private UserService userService;

    @Get
    public List<CDUser> getUsersByCustomer() {
        return userService.getUsersByCustomer(Integer.parseInt(getAttribute("customerId")));
    }

    @Post
    public CDUser saveUser(CDUser user) {
        return userService.saveUserToCustomer(user, Integer.parseInt(getAttribute("customerId")));
    }
}
