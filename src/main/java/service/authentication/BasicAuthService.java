package service.authentication;

import com.google.inject.Inject;
import org.restlet.Context;
import org.restlet.data.ChallengeScheme;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MapVerifier;

public class BasicAuthService {

    private ChallengeAuthenticator authenticator;

    @Inject
    public BasicAuthService(Context context) {
        this.authenticator = new ChallengeAuthenticator(context, ChallengeScheme.HTTP_BASIC, "CarDealer");
        MapVerifier verifier = new MapVerifier();
        verifier.getLocalSecrets().put("example", "pwd".toCharArray());
        authenticator.setVerifier(verifier);
    }

    public ChallengeAuthenticator getAuthenticator() {
        return authenticator;
    }

}
