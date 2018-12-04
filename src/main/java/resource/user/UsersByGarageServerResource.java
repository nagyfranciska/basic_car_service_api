package resource.user;

import com.google.inject.Inject;
import model.CDUser;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import service.model.UserService;

import java.util.List;

public class UsersByGarageServerResource extends ServerResource {

    @Inject
    private UserService userService;

    @Get
    public List<CDUser> getUsersByGarage() {
        return userService.getUsersByGarage(Integer.parseInt(getAttribute("garageId")));
    }

    @Post
    public CDUser saveUsersToGarage(CDUser user) {
        return userService.saveUserToGarage(user, Integer.parseInt(getAttribute("garageId")));
    }

}
