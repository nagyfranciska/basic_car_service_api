package resource;

import com.google.inject.Inject;
import model.CDUser;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import service.UserService;

import java.util.List;

public class UsersByGarageServerResource extends ServerResource {

    @Inject
    private UserService userService;

    @Get
    public List<CDUser> getUsersByGarage() {
        return userService.getUsersByGarage(Integer.parseInt(getAttribute("garageId")));
    }

    @Post
    public CDUser saveUser(CDUser user) {
        return userService.saveUserToGarage(user, Integer.parseInt(getAttribute("garageId")));
    }
}
