package service;


import com.google.inject.Inject;
import model.User;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.routing.Filter;

import java.util.List;

public class ApiPermissionFilter extends Filter {

    @Inject
    private UserService userService;

    public ApiPermissionFilter() {}

    public ApiPermissionFilter(Context context) {
        super(context);
    }

    @Override
    protected int beforeHandle(Request request, Response response) {

        List<String> segments = request.getOriginalRef().getSegments();

        if (request.getClientInfo() != null && request.getClientInfo().getUser() != null) {

            User user = userService.getUserByName(request.getClientInfo().getUser().getName());
            boolean hasPerm = hasPermission(user, segments);

            if (hasPerm) {
                return CONTINUE;
            } else {
                response.setStatus(Status.CLIENT_ERROR_FORBIDDEN);
            }
        }
        return STOP;
    }

    private boolean hasPermission(User user, List<String> segments) {

        // can be customers, garages or me
         String segment = segments.get(1);

         if (segment.equals("garages") && user.getGarage() != null) {
             return true;
         } else if (segment.equals("customers") && user.getCustomer() != null) {
             return true;
         } return (segment.equals("me"));
    }
}
