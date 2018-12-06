package resource;

import com.google.inject.Inject;
import freemarker.template.Configuration;
import model.User;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.ContextTemplateLoader;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.ext.oauth.AuthorizationBaseServerResource;
import org.restlet.ext.oauth.OAuthException;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.security.SecretVerifier;
import service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LoginPageServerResource extends AuthorizationBaseServerResource {

    @Inject
    UserService userService;

    @Get("html")
    @Post("html")
    public Representation getPage() throws OAuthException {
        getLogger().info("Get Login");
        String userId = getQueryValue("user_id");
        HashMap<String, Object> data = new HashMap<String, Object>();
        if (userId != null && !userId.isEmpty()) {
            String password = getQueryValue("password");
            //TODO: into service and dao
            List<User> userList = new CopyOnWriteArrayList<>();
            userList.add((User) userService.getUsers().stream().filter(u -> u.getName().equals(userId)));
            User user = userList.get(0);
            if (user == null) {
                data.put("error", "Authentication failed.");
                data.put("error_description", "ID is invalid.");
            } else {
                boolean result = SecretVerifier.compare(password.toCharArray(),
                        user.getPassword().toCharArray());
                if (result) {
                    getAuthSession().setScopeOwner(userId);
                    String uri = getQueryValue("continue");
                    redirectTemporary(uri);
                    return new EmptyRepresentation();
                } else {
                    data.put("error", "Authentication failed.");
                    data.put("error_description", "Password is invalid.");
                }
            }
        }

        String continueURI = getQueryValue("continue");
        TemplateRepresentation response = getLoginPage("login.html");
        data.put("continue", continueURI);
        response.setDataModel(data);

        return response;
    }

    protected TemplateRepresentation getLoginPage(String loginPage) {
        Configuration config = new Configuration();
        config.setTemplateLoader(new ContextTemplateLoader(getContext(),
                "clap:///"));
        getLogger().fine("loading: " + loginPage);
        return new TemplateRepresentation(loginPage, config,
                MediaType.TEXT_HTML);
    }
}
