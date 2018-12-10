package oauth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.utility.RandomString;
import org.restlet.ext.oauth.internal.AbstractClientManager;
import org.restlet.ext.oauth.internal.Client;

import javax.persistence.*;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Entity
public class FinalClient {

    @Id
    private String id;
    private String secret;
    private Client.ClientType clientType;
    private String properties;
    @ElementCollection
    @CollectionTable(
            name = "CLIENT_REDIRECT_URIS",
            joinColumns = @JoinColumn(name = "CLIENT_ID")
    )
    private List<String> redirectURIs;

    @Transient
    private Map<Client.ClientType, Object[]> props;

    public FinalClient() {}

    public FinalClient(List<String> redirectURIs) {

        this.redirectURIs = redirectURIs;
        this.id = RandomString.make(3);
        this.secret = RandomString.make(6);
        this.clientType = Client.ClientType.PUBLIC;
        this.props = new EnumMap<Client.ClientType, Object[]>(Client.ClientType.class);
        props.put(Client.ClientType.PUBLIC, AbstractClientManager.DEFAULT_SUPPORTED_FLOWS_PUBLIC);
        this.properties = "supported_flows: " + Arrays.toString(props.get(Client.ClientType.PUBLIC));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Client.ClientType getClientType() {
        return clientType;
    }

    public void setClientType(Client.ClientType clientType) {
        this.clientType = clientType;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public List<String> getRedirectURIs() {
        return redirectURIs;
    }

    public void setRedirectURIs(List<String> redirectURIs) {
        this.redirectURIs = redirectURIs;
    }

    public Map<Client.ClientType, Object[]> getProps() {
        return props;
    }

    public void setProps(Map<Client.ClientType, Object[]> props) {
        this.props = props;
    }
}
