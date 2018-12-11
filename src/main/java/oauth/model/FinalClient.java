//package oauth.model;
//
//import net.bytebuddy.utility.RandomString;
//import org.restlet.ext.oauth.GrantType;
//import org.restlet.ext.oauth.ResponseType;
//import org.restlet.ext.oauth.internal.AbstractClientManager;
//import org.restlet.ext.oauth.internal.Client;
//
//import javax.persistence.*;
//import java.util.*;
//
//@Entity
//public class FinalClient implements Client{
//
//    @Id
//    private String id;
//    private String secret;
//    private Client.ClientType clientType;
//    private String properties;
//    private Set<GrantType> grantTypes;
//    @ElementCollection
//    @CollectionTable(
//            name = "CLIENT_REDIRECT_URIS",
//            joinColumns = @JoinColumn(name = "CLIENT_ID")
//    )
//    private List<String> redirectURIs;
//
//    @Transient
//    private Map<Client.ClientType, Object[]> props;
//
//    public FinalClient() {}
//
//
//    {
//        "id": "345345",
//        "name": "Default web client",
//        "secret": "34534w5rw3445r435r43",
//        "grantTypes": ["implicit", "password"],
//        "responseTypes": ["code", "token"],
//        "clientType": "confidential",
//        "redirectUris": ["http://localhost", "http://onbox.hu"]
//    }
//
////    abstractot ne implementalj
////
////    getProps --> en allitom ossze mapbe a fieldek alapjan
////    {
////        "responseTypes": this.responseTypes
////    }
////
////
////
////    ClientManager
////
//
//
//
//
//
//    public FinalClient(List<String> redirectURIs) {
//
//        this.redirectURIs = redirectURIs;
//        this.id = RandomString.make(3);
//        this.secret = RandomString.make(6);
//        this.clientType = Client.ClientType.PUBLIC;
//        this.props = new EnumMap<Client.ClientType, Object[]>(Client.ClientType.class);
//        props.put(Client.ClientType.PUBLIC, AbstractClientManager.DEFAULT_SUPPORTED_FLOWS_PUBLIC);
//        this.properties = "supported_flows: " + Arrays.toString(props.get(Client.ClientType.PUBLIC));
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getSecret() {
//        return secret;
//    }
//
//    public void setSecret(String secret) {
//        this.secret = secret;
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//    @Override
//    public String getClientId() {
//        return null;
//    }
//
//    @Override
//    public char[] getClientSecret() {
//        return new char[0];
//    }
//
//    public Client.ClientType getClientType() {
//        return clientType;
//    }
//
//    public void setClientType(Client.ClientType clientType) {
//        this.clientType = clientType;
//    }
//
//    public String getProperties() {
//        return properties;
//    }
//
//    public void setProperties(String properties) {
//        this.properties = properties;
//    }
//
//    public List<String> getRedirectURIs() {
//        return redirectURIs;
//    }
//
//    @Override
//    public boolean isGrantTypeAllowed(GrantType grantType) {
//        return grantTypes.contains(grantType);
//    }
//
//    @Override
//    public boolean isResponseTypeAllowed(ResponseType responseType) {
//        return false;
//    }
//
//    public void setRedirectURIs(List<String> redirectURIs) {
//        this.redirectURIs = redirectURIs;
//    }
//
//    public Map<Client.ClientType, Object[]> getProps() {
//        return props;
//    }
//
//    public void setProps(Map<Client.ClientType, Object[]> props) {
//        this.props = props;
//    }
//}
