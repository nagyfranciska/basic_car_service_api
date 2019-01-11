package oauth;

import com.google.inject.Inject;
import freemarker.template.Configuration;
import model.CDUser;
import org.mindrot.jbcrypt.BCrypt;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.ContextTemplateLoader;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.ext.oauth.AuthorizationBaseServerResource;
import org.restlet.ext.oauth.OAuthException;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import service.model.UserService;

import java.util.HashMap;

public class LoginServerResource extends AuthorizationBaseServerResource {

    @Inject
    private UserService userService;

    @Get("html")
    @Post("html")
    public Representation getPage() throws OAuthException {
        String username = getQueryValue("username");
        HashMap<String, Object> data = new HashMap<String, Object>();
        if (username != null && !username.isEmpty()) {
            String password = getQueryValue("password");
            System.out.println("USER: " + username + " | PWD: " + password);
            CDUser user = userService.getUserByUsername(username);
            if (user == null) {
                data.put("error", "Authentication failed.");
                data.put("error_description", "ID is invalid.");
            } else {
                if (BCrypt.checkpw(password, user.getPassword())) {
                    getAuthSession().setScopeOwner(username);
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
        TemplateRepresentation response = getLoginPage();
        data.put("continue", continueURI);
        response.setDataModel(data);

        return response;
    }

    private TemplateRepresentation getLoginPage() {
        Configuration config = new Configuration();
        config.setTemplateLoader(new ContextTemplateLoader(getContext(),
                "clap:///"));
        return new TemplateRepresentation("login.html", config,
                MediaType.TEXT_HTML);
    }
}

