package resource;

import com.google.inject.Inject;
import model.User;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import service.UserService;

import java.util.List;

public class UsersByGarageServerResource extends ServerResource {

    @Inject
    private UserService userService;

    @Get
    public List<User> getUsersByGarage() {
        return userService.getUsersByGarage(Integer.parseInt(getAttribute("garageId")));
    }

    @Post
    public User saveUser(User user) {
        return userService.saveUserToGarage(user, Integer.parseInt(getAttribute("garageId")));
    }
}
