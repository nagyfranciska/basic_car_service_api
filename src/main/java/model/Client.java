package model;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    @Column(name = "ID", unique = true, nullable = false)
    private String clientId;

    @Column(name = "SECRET", unique = true, nullable = false)
    private char[] clientSecret;

    @Column(name = "TYPE", nullable = false)
    private Client.ClientType clientType;

    @Transient
    private Map<String, Object> properties;

    @Column(name = "PROPERTIES", nullable = false)
    private String propertiesJson;

    @Column(name = "REDIRECT_URI", nullable = false)
    @ElementCollection()
    @CollectionTable(name = "CLIENT_REDIRECT_URIS", joinColumns = @JoinColumn(name = "CLIENT_ID"))
    private List<String> redirectUrisList;

    @Transient
    private String[] redirectUris;

    public Client(String clientId, char[] clientSecret, ClientType clientType,
                  String[] redirectUris, Map<String, Object> properties) throws JsonProcessingException {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientType = clientType;
        this.properties = properties;
        this.propertiesJson = new ObjectMapper().writeValueAsString(properties);
        this.redirectUris = redirectUris;
        this.redirectUrisList = List.of(redirectUris);
    }

    public Client() {
    }

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
        return this.properties;
    }

    public String getPropertiesJson() {
        return this.propertiesJson;
    }

    public boolean convertProperties() {
        if (this.properties == null) {
            try {
                this.properties = new ObjectMapper().readValue(this.propertiesJson, Map.class);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        if (this.propertiesJson == null) {
            try {
                this.propertiesJson = new ObjectMapper().writeValueAsString(this.properties);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean convertRedirectUris() {
        if (this.redirectUris == null) {
            try {
                this.redirectUris = redirectUrisList.toArray(String[]::new);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        if (this.redirectUrisList == null) {
            try {
                this.redirectUrisList = List.of(redirectUris);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public String[] getRedirectURIs() {
        return this.redirectUris;
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
        return Arrays.asList(this.properties.get("supported_flows")).contains(flow.toString());
    }

}
