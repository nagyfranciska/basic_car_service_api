//package oauth.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.google.inject.Inject;
//import oauth.dao.ClientDAO;
//import oauth.model.Client;
//import org.restlet.ext.oauth.internal.AbstractClientManager;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//public class ClientService extends AbstractClientManager {
//
//    @Inject
//    ClientDAO clientDAO;
//
//    @Override
//    public org.restlet.ext.oauth.internal.Client createClient(org.restlet.ext.oauth.internal.Client.ClientType clientType, String[] redirectURIs, Map<String, Object> properties) {
//        return super.createClient(clientType, redirectURIs, properties);
//    }
//
//    @Override
//    protected org.restlet.ext.oauth.internal.Client createClient(String clientId, char[] clientSecret, org.restlet.ext.oauth.internal.Client.ClientType clientType,
//                                                                 String[] redirectURIs, Map<String, Object> properties) {
//
//        try{
//            return clientDAO.save(new Client(clientId, clientSecret, clientType, redirectURIs, properties));
//        } catch (JsonProcessingException jpe) {
//            return null;
//        }
//    }
//
//    public void deleteClient(String s) {
//        clientDAO.delete(s);
//    }
//
//    public Client findById(String id) {
//        return clientDAO.findById(id);
//    }
//
//    public List<Client> findAll() {
//        return clientDAO.findAll();
//    }
//}
