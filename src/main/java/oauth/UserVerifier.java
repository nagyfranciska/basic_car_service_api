package oauth;

import com.google.inject.Inject;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import dao.UserDAO;
import model.CDUser;
import org.mindrot.jbcrypt.BCrypt;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.security.LocalVerifier;
import org.restlet.security.SecretVerifier;
import org.restlet.security.User;


public class UserVerifier extends SecretVerifier {

    @Inject
    UserDAO userDAO;

    public UserVerifier() {}

    //Verifies that the proposed secret is correct for the specified request.
    @Override
    public int verify(Request request, Response response) {
        String identifier = this.getIdentifier(request, response);
        char[] secret = this.getSecret(request, response);
        int result = this.verify(identifier, secret);
        if (result == RESULT_VALID) {
            request.getClientInfo().setUser(this.createUser(identifier, request, response));
        }
        return result;
    }

    //is above verifier is correct, it calls for this method and create new user
    // >> it is customised for CDUSer - for this, made USER parent class' fields (extended by CDUser) transient in model
    @Override
    protected User createUser(String identifier, Request request, Response response) {
        return new CDUser(identifier);
    }

    //Verifies that the identifier/secret couple is valid.
    @Override
    public int verify(String s, char[] chars) {
        String password = String.copyValueOf(chars);
        String hashedPassword = userDAO.getPasswordByUsername(s);
        return (BCrypt.checkpw(password, hashedPassword) ? RESULT_VALID : RESULT_INVALID);
    }
}
