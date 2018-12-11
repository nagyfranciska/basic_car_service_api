package oauth.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.utility.RandomString;
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
    private String id;
    private String secret;

    @Transient
    private Client.ClientType clientType;
    @Column(name = "CLIENT_TYPE")
    private String type;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "CLIENT_REDIRECT_URIS",
            joinColumns = @JoinColumn(name = "CLIENT_ID")
    )
    private List<String> redirectUris;

    @Transient
    private Map<String, Object> properties;
    @Column(name = "PROPERTIES")
    private String props;

    public Client() {
    }

    public Client(ClientType clientType, String[] redirectURIs, Map<String, Object> properties)
//            throws JsonProcessingException
    {
        this.id = RandomString.make(5);
        this.secret = RandomString.make(3);
        this.clientType = clientType;
        this.type = clientType.toString();

        if (properties != null && properties.containsValue(ResponseType.token)) {
            this.properties = properties;
        } else {
            properties = new HashMap<>();
            properties.put(org.restlet.ext.oauth.internal.Client.PROPERTY_SUPPORTED_FLOWS, ResponseType.token);
        }
//        this.props = new ObjectMapper().writeValueAsString(properties);
        //or:
        if (properties.containsKey("supported_flows")) {
            this.props = "supported_flows: " + properties.get("supported_flows").toString();
        }
        this.redirectUris = List.of(redirectURIs);
    }

    @Override
    public String getClientId() {
        return this.id;
    }

    @Override
    public char[] getClientSecret() {
        return this.secret.toCharArray();
    }

    @Override
    public ClientType getClientType() {
        return this.clientType;
    }

    @Override
    public Map<String, Object> getProperties() {
        return this.properties;
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