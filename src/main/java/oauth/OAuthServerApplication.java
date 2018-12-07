package oauth;

import com.google.inject.Inject;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.ext.oauth.*;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

public class OAuthServerApplication extends Application {

    @Inject
    private ClientService cdClientManager;

    @Inject
    private CDTokenManager cdTokenManager;

    @Override
    public synchronized Restlet createInboundRoot() {
        Router router = new Router(getContext());

        getContext().getAttributes().put(ClientService.class.getName(), cdClientManager);
        getContext().getAttributes().put(CDTokenManager.class.getName(), cdTokenManager);

        // Setup Authorize Endpoint
        router.attach("/authorize", AuthorizationServerResource.class);
        router.attach(HttpOAuthHelper.getAuthPage(getContext()), AuthPageServerResource.class);
        HttpOAuthHelper.setAuthPageTemplate("authorize.html", getContext());
        HttpOAuthHelper.setAuthSkipApproved(true, getContext());
        HttpOAuthHelper.setErrorPageTemplate("error.html", getContext());
        router.attach(HttpOAuthHelper.getLoginPage(getContext()), LoginServerResource.class);

        // Setup Token Auth for Resources Server
        router.attach("/token_auth", TokenAuthServerResource.class);

        //TODO: Find out what is this
        final Directory resources = new Directory(getContext(), "clap://system/resources");

        router.attach("", resources);

        return router;
    }

}
