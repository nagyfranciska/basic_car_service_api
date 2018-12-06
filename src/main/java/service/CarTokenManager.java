package service;

import org.restlet.ext.oauth.OAuthException;
import org.restlet.ext.oauth.internal.*;

public class CarTokenManager extends AbstractTokenManager {


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
    public Token generateToken(Client client, String s, String[] strings) throws OAuthException {
        return null;
    }

    @Override
    public Token refreshToken(Client client, String s, String[] strings) throws OAuthException {
        return null;
    }

    @Override
    public AuthSession restoreSession(String s) throws OAuthException {
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
    public String storeSession(AuthSession authSession) throws OAuthException {
        return null;
    }

    @Override
    public Token validateToken(String s) throws OAuthException {
        return null;
    }
}
