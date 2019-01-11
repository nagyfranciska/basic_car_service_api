package oauth;

import org.restlet.ext.oauth.OAuthException;
import org.restlet.ext.oauth.internal.AbstractTokenManager;
import org.restlet.ext.oauth.internal.AuthSession;
import org.restlet.ext.oauth.internal.Client;
import org.restlet.ext.oauth.internal.Token;

public class TokenService extends AbstractTokenManager {

    //TODO: Implement all this shit
    @Override
    public Token findToken(Client client, String s) {
        return null;
    }

    @Override
    public Token[] findTokens(Client client) {
        return new Token[0];
    }

    @Override
    public Token[] findTokens(String s) {
        return new Token[0];
    }

    @Override
    public Token generateToken(Client client, String s, String[] strings) {
        return null;
    }

    @Override
    public Token refreshToken(Client client, String s, String[] strings) {
        return null;
    }

    @Override
    public AuthSession restoreSession(String s) {
        return null;
    }

    @Override
    public void revokeAllTokens(Client client) {

    }

    @Override
    public void revokeAllTokens(String s) {

    }

    @Override
    public void revokeToken(Client client, String s) {

    }

    @Override
    public String storeSession(AuthSession authSession) {
        return null;
    }

    @Override
    public Token validateToken(String s) {
        return null;
    }
}
