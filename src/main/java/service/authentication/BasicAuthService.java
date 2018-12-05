package service.authentication;

import application.CarServerApplication;
import com.google.inject.Inject;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.data.ChallengeScheme;
import org.restlet.security.ChallengeAuthenticator;

public class BasicAuthService {

    @Inject
    private UserVerifier userVerifier;

    @Inject
    private CarServerApplication application;

    private ChallengeAuthenticator authenticator;

    public BasicAuthService() {
    }

    public ChallengeAuthenticator getAuthenticator() {
        if (this.authenticator == null) {
            Context context = application.getContext();
            this.authenticator = new ChallengeAuthenticator(context, ChallengeScheme.HTTP_BASIC, "CarDealer");
            authenticator.setVerifier(userVerifier);
            return this.authenticator;
        } else {
            return this.authenticator;
        }
    }

}
