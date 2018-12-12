package resource;

import com.google.inject.Inject;
import model.User;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import service.UserService;

public class MeServerResource extends ServerResource {

    @Inject
    private UserService userService;

    @Get
    public User currentUser() {
        return userService.getUserByName(getClientInfo().getUser().getName());
    }
}
