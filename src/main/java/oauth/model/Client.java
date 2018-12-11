package oauth.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.restlet.ext.oauth.GrantType;
import org.restlet.ext.oauth.ResponseType;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Client implements org.restlet.ext.oauth.internal.Client {

    @Id
    private String clientId;
    private char[] clientSecret;
    private Client.ClientType clientType;
    private String properties;
    private List<String> redirectUris;



    public Client() {
    }

    public Client(ClientType clientType, String[] redirectURIs, Map<String, Object> properties) {
    }

//
//    public Client(String clientId, char[] clientSecret, ClientType clientType,
//                  String[] redirectUris, Map<String, Object> properties) {
//        this.clientId = clientId;
//        this.clientSecret = clientSecret;
//        this.clientType = clientType;
////        this.properties = new ObjectMapper().writeValueAsString(properties);
//        this.redirectUris = List.of(redirectUris);
//    }

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public char[] getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public ClientType getClientType() {
        return this.clientType;
    }

    @Override
    public Map<String, Object> getProperties() {
        try {
            return new ObjectMapper().readValue(this.properties, HashMap.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String[] getRedirectURIs() {
        return (String[]) this.redirectUris.toArray();
    }

    @Override
    public boolean isGrantTypeAllowed(GrantType grantType) {
        return this.isFlowSupported(grantType);
    }

    @Override
    public boolean isResponseTypeAllowed(ResponseType responseType) {
        return this.isFlowSupported(responseType);
    }

    private boolean isFlowSupported(Object flow) {
        return Arrays.asList((Object[]) this.getProperties().get("supported_flows")).contains(flow);
    }
}