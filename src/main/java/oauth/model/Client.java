package oauth.model;

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


    //TODO:
    // az AuthServerResource methodjai lekerik a ClientType-ot es a Properties-t. Null az ertekuk
    // mert nincsenek lementve adatbazisba, ezert a ket dummy adatbol (props es type) beallitva adnam oket,
    // de valamiert nem mukodik. :/ -- > kikommenteltem, es most siman megnezem h a dummyban szerepel-e a flow Object;


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

    public Client(ClientType clientType, String[] redirectURIs, Map<String, Object> properties) {
        this.id = RandomString.make(5);
        this.secret = RandomString.make(3);
        this.clientType = clientType;
        this.type = clientType.toString();

        // if we get responseTypes, accept them
        if (properties != null) {
            this.properties = properties;
            // if properties is null, set it by clientType
        } else if(clientType.equals(ClientType.CONFIDENTIAL)) {
            properties = new HashMap<>();
            properties.put(org.restlet.ext.oauth.internal.Client.PROPERTY_SUPPORTED_FLOWS, ResponseType.code);
        } else if(clientType.equals(ClientType.PUBLIC)) {
            properties = new HashMap<>();
            properties.put(org.restlet.ext.oauth.internal.Client.PROPERTY_SUPPORTED_FLOWS, ResponseType.token);
        }

        if (properties != null) {
            this.props = "supported_flows: " + properties.get("supported_flows").toString();
        }
        this.redirectUris = List.of(redirectURIs);
    }

    @Override
    public String getClientId() {
        return id;
    }

    @Override
    public char[] getClientSecret() {
        return secret.toCharArray();
    }

    @Override
    public ClientType getClientType() {
        return clientType;
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }

    @Override
    public String[] getRedirectURIs() {
        String[] uris = new String[redirectUris.size()];
        for (int i = 0; i < uris.length; i++) {
            uris[i] = redirectUris.get(i);
        }
        return uris;
    }

    @Override
    public boolean isGrantTypeAllowed(GrantType grantType) {
        return isFlowSupported(grantType);
    }

    @Override
    public boolean isResponseTypeAllowed(ResponseType responseType) {
        return isFlowSupported(responseType);
    }

    private boolean isFlowSupported(Object flow) {
        return props.contains(flow.toString());
//        return Arrays.asList((Object[]) getProperties().get("supported_flows")).contains(flow);
    }

    public String getClientSecretAsString() {
        return secret;
    }

    public String getPropertiesAsString() {
        return props;
    }
}