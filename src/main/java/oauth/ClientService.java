package oauth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.Inject;
import dao.ClientDAO;
import org.restlet.ext.oauth.internal.AbstractClientManager;
import org.restlet.ext.oauth.internal.Client;

import java.util.Map;

public class ClientService extends AbstractClientManager {

    @Inject
    private ClientDAO clientDAO;

    @Override
    public Client createClient(Client.ClientType clientType, String[] redirectURIs, Map<String, Object> properties) {
        return super.createClient(clientType, redirectURIs, properties);
    }

    @Override
    protected Client createClient(String id, char[] secret, Client.ClientType clientType,
                                  String[] redirectURIs, Map<String, Object> properties) {
        try {
            return clientDAO.save(new model.Client(id, secret, clientType, redirectURIs, properties));
        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteClient(String clientId) {
        clientDAO.delete(clientId);
    }

    @Override
    public Client findById(String clientId) {
        return clientDAO.findById(clientId);
    }

}
