package resource;

import com.google.inject.Inject;
import model.User;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import service.UserService;

public class UserServerResource extends ServerResource {

    @Inject
    private UserService userService;

    @Get
    public User getUser() {
        return userService.getUserById(Integer.parseInt(getAttribute("userId")));
    }

    @Put
    public User updateUser(User user) {
        return userService.updateUser(user, Integer.parseInt(getAttribute("userId")));
    }

    @Delete
    public User deleteUser() {
        return userService.deleteUser(Integer.parseInt(getAttribute("userId")));
    }


}
