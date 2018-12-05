package service.authentication;

import com.google.inject.Inject;
import dao.UserDAO;
import model.CDUser;
import org.mindrot.jbcrypt.BCrypt;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.security.SecretVerifier;
import org.restlet.security.User;

public class UserVerifier extends SecretVerifier {

    @Inject
    private UserDAO userDAO;

    @Override
    public int verify(Request request, Response response) {
        int result = RESULT_MISSING;
        if (request.getChallengeResponse() != null) {
            String identifier = this.getIdentifier(request, response);
            char[] secret = this.getSecret(request, response);
            result = this.verify(identifier, secret);
            if (result == RESULT_VALID) {
                request.getClientInfo().setUser(this.createUser(identifier, request, response));
            }
        }

        return result;
    }

    @Override
    public int verify(String identifier, char[] secret) {
        String pwd = String.copyValueOf(secret);
        String pwdHashed = userDAO.getPasswordByUsername(identifier);
        if (pwdHashed == null) {
            return RESULT_UNKNOWN;
        } else {
            return(pwd.equals(pwdHashed) ? RESULT_VALID : RESULT_INVALID);
        }

    }

    @Override
    protected User createUser(String identifier, Request request, Response response) {
        return new CDUser(identifier);
    }
}
