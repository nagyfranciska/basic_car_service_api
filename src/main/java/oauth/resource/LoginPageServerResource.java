package oauth.resource;

import com.google.inject.Inject;
import freemarker.template.Configuration;
import model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.ContextTemplateLoader;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.ext.oauth.AuthorizationBaseServerResource;
import org.restlet.ext.oauth.OAuthException;
import org.restlet.ext.oauth.internal.AuthSession;
import org.restlet.ext.oauth.internal.RedirectionURI;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import service.UserService;

import java.util.HashMap;

public class LoginPageServerResource extends AuthorizationBaseServerResource {

    @Inject
    private UserService userService;

    //TODO: User model refact --> id to String

    @Get("html")
    @Post("html")
    public Representation getPage() throws OAuthException {
        getLogger().info("Get Login");
        String username = getQueryValue("user_id");
        HashMap<String, Object> data = new HashMap<String, Object>();
        if (username != null && !username.isEmpty()) {
            String password = getQueryValue("password");
            getLogger().info("User=" + username + ", Pass=" + password);

            User user = userService.getUserByName(username);
            if (user == null) {
                data.put("error", "Authentication failed.");
                data.put("error_description", "ID is invalid.");
            } else {
                if (BCrypt.checkpw(password, user.getPassword())) {
                    AuthSession session = getAuthSession();
                    session.setScopeOwner(username);
                    String uri = getQueryValue("continue");
                    getLogger().info("URI: " + uri);
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
        config.setTemplateLoader(new ContextTemplateLoader(getContext(), "clap:///"));
        getLogger().fine("loading: " + loginPage);
        return new TemplateRepresentation(loginPage, config, MediaType.TEXT_HTML);
    }
}
