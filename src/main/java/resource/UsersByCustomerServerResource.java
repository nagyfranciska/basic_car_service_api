package resource;

import com.google.inject.Inject;
import model.User;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import service.UserService;

import java.util.List;

public class UsersByCustomerServerResource extends ServerResource {

    @Inject
    private UserService userService;

    @Get
    public List<User> getUsersByCustomer() {
        return userService.getUsersByCustomer(Integer.parseInt(getAttribute("customerId")));
    }

    @Post
    public User saveUser(User user) {
        return userService.saveUserToCustomer(user, Integer.parseInt(getAttribute("customerId")));
    }
}
