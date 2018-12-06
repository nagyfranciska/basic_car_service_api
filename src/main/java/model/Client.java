package model;

import net.bytebuddy.utility.RandomString;
import org.restlet.ext.oauth.GrantType;
import org.restlet.ext.oauth.ResponseType;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;


@Entity
public class Client implements org.restlet.ext.oauth.internal.Client {

    @Id
    private String clientId;
    private char[] secret;
    private ClientType type;
    private Map<String, Object> properties;
    private String[] redirectURI;

    public Client() {}

    public Client(char[] secret, ClientType type, Map<String, Object> properties, String[] redirectURI) {
        this.clientId = RandomString.make(10);
        this.secret = secret;
        this.type = type;
        this.properties = properties;
        this.redirectURI = redirectURI;
    }

    @Override
    public String getClientId() {
        return null;
    }

    @Override
    public char[] getClientSecret() {
        return new char[0];
    }

    @Override
    public ClientType getClientType() {
        return null;
    }

    @Override
    public Map<String, Object> getProperties() {
        return null;
    }

    @Override
    public String[] getRedirectURIs() {
        return new String[0];
    }

    @Override
    public boolean isGrantTypeAllowed(GrantType grantType) {
        return false;
    }

    @Override
    public boolean isResponseTypeAllowed(ResponseType responseType) {
        return false;
    }
}
