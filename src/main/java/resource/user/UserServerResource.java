package resource.user;

import com.google.inject.Inject;
import model.CDUser;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import service.model.UserService;

public class UserServerResource extends ServerResource {

    @Inject
    private UserService userService;

    @Get
    public CDUser getUser() {
        return userService.getUserById(Integer.parseInt(getAttribute("userId")));
    }

    @Put
    public CDUser updateUser(CDUser user) {
        return userService.updateUser(user, Integer.parseInt(getAttribute("userId")));
    }

    @Delete
    public CDUser deleteUser() {
        return userService.deleteUser(Integer.parseInt(getAttribute("userId")));
    }

}
