package oauth;

import com.google.inject.Inject;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.ext.oauth.*;
import org.restlet.ext.oauth.internal.ClientManager;
import org.restlet.ext.oauth.internal.TokenManager;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import resource.LoginPageServerResource;
import service.CarClientManager;
import service.CarTokenManager;

public class OauthApplication extends Application {

    @Inject
    CarClientManager clientManager;

    @Inject
    CarTokenManager tokenManager;

    @Override
    public synchronized Restlet createInboundRoot() {
        Router router = new Router(getContext());

        getContext().getAttributes().put(ClientManager.class.getName(), clientManager);
        getContext().getAttributes().put(TokenManager.class.getName(), tokenManager);

        // Setup Authorize Endpoint
        router.attach("/authorize", AuthorizationServerResource.class);
        router.attach(HttpOAuthHelper.getAuthPage(getContext()), AuthPageServerResource.class);
        HttpOAuthHelper.setAuthPageTemplate("authorize.html", getContext());
        HttpOAuthHelper.setAuthSkipApproved(true, getContext());
        HttpOAuthHelper.setErrorPageTemplate("error.html", getContext());
        router.attach(HttpOAuthHelper.getLoginPage(getContext()), LoginPageServerResource.class);

//        // Setup Token Endpoint
//        ChallengeAuthenticator clientAuthenticator = new ChallengeAuthenticator(getContext(), ChallengeScheme.HTTP_BASIC, "Realm of Despair");
//        ClientVerifier clientVerifier = new ClientVerifier(getContext());
//        clientVerifier.setAcceptBodyMethod(true);
//        clientAuthenticator.setVerifier(clientVerifier);
//        clientAuthenticator.setNext(AccessTokenServerResource.class);
//        router.attach("/token", clientAuthenticator);

        // Setup Token Auth for Resources Server
        router.attach("/token_auth", TokenAuthServerResource.class);

        final Directory resources = new Directory(getContext(), "clap://system/resources");

        router.attach("", resources);

        return router;
    }
}
