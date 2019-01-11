package oauth;

import com.google.inject.Inject;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.ext.guice.FinderFactory;
import org.restlet.ext.oauth.*;
import org.restlet.ext.oauth.internal.ClientManager;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

import java.util.logging.Logger;

public class OAuthServerApplication extends Application {

    @Inject
    private ClientService cdClientManager;

    @Inject
    private TokenService cdTokenManager;

    @Inject
    private FinderFactory finder;

    @Override
    public synchronized Restlet createInboundRoot() {
        Router router = new Router(getContext());

        getContext().getAttributes().put(ClientManager.class.getName(), cdClientManager);
        getContext().getAttributes().put(TokenService.class.getName(), cdTokenManager);

        // Setup Authorize Endpoint
        router.attach("/authorize", finder.finder(AuthorizationServerResource.class));
        router.attach(HttpOAuthHelper.getAuthPage(getContext()), finder.finder(AuthPageServerResource.class));
        HttpOAuthHelper.setAuthPageTemplate("authorize.html", getContext());
        HttpOAuthHelper.setAuthSkipApproved(true, getContext());
        HttpOAuthHelper.setErrorPageTemplate("error.html", getContext());
        router.attach(HttpOAuthHelper.getLoginPage(getContext()), finder.finder(LoginServerResource.class));

        // Setup Token Auth for Resources Server
        router.attach("/token_auth", finder.finder(TokenAuthServerResource.class));

        //TODO: Find out what is this
        final Directory resources = new Directory(getContext(), "clap://system/resources");

        router.attach("", resources);

        return router;
    }

}
