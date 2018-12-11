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


    //TODO: hiaba a properties megfelelo beallitasa, crealasnal semmi gond, de ha
    // az AuthServerResource methodjai lekerik a getterrel oket, null az ertekuk (properties, ClientType)
    // most a ket dummy adatbol (props es type) alakitom at es kuldom vissza a value-kat, de ennek jol kene mukodni :/

    // problema: this hasynalata a getter/setterben (vhol volt, vhol nem volt --> megnezni, kell-e)


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
        if (type.equals(ClientType.CONFIDENTIAL.toString())) {
        return ClientType.CONFIDENTIAL;
        }
        return ClientType.PUBLIC;
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> map = new HashMap<>();
        if (props.contains("token")) {
           map.put(org.restlet.ext.oauth.internal.Client.PROPERTY_SUPPORTED_FLOWS, ResponseType.token);
           return map;
        }
        map.put(org.restlet.ext.oauth.internal.Client.PROPERTY_SUPPORTED_FLOWS, ResponseType.code);
        return map;
    }

    @Override
    public String[] getRedirectURIs() {
        String[] uris = {redirectUris.get(0)};
        return uris;

//        return (String[]) redirectUris.toArray();
    }

    @Override
    public boolean isGrantTypeAllowed(GrantType grantType) {
        return true;
//        return isFlowSupported(grantType);
    }

    @Override
    public boolean isResponseTypeAllowed(ResponseType responseType) {
        return true;
//        return isFlowSupported(responseType);
    }

    private boolean isFlowSupported(Object flow) {
        return Arrays.asList((Object[]) getProperties().get("supported_flows")).contains(flow);
    }

    public String getClientSecretAsString() {
        return secret;
    }

    public String getPropertiesAsString() {
        return props;
    }
}