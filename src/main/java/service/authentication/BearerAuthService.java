package service.authentication;

import application.CarServerApplication;
import com.google.inject.Inject;
import oauth.CDTokenManager;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Reference;
import org.restlet.ext.oauth.TokenVerifier;
import org.restlet.security.ChallengeAuthenticator;

public class BearerAuthService {

    @Inject
    private CarServerApplication application;

    private ChallengeAuthenticator authenticator;

    public BearerAuthService() {
    }

    public ChallengeAuthenticator getAuthenticator() {
        if (this.authenticator == null) {
            Context context = application.getContext();
            Reference tokenAuthReference = new Reference("riap://component/oauth/token_auth");
            //TODO: Implement custom TokenVerifier, or find a way to overwrite created User
            TokenVerifier tokenVerifier = new TokenVerifier(tokenAuthReference);
            this.authenticator = new ChallengeAuthenticator(context, ChallengeScheme.HTTP_OAUTH_BEARER, "CarDealer");
            authenticator.setVerifier(tokenVerifier);
            return this.authenticator;
        } else {
            return this.authenticator;
        }
    }

}
