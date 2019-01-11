package oauth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.Inject;
import dao.ClientDAO;
import org.json.JSONException;
import org.restlet.ext.oauth.internal.AbstractClientManager;
import org.restlet.ext.oauth.internal.Client;

import java.util.List;
import java.util.Map;

public class ClientService extends AbstractClientManager {

    @Inject
    private ClientDAO clientDAO;

    @Override
    public Client createClient(Client.ClientType clientType, String[] redirectURIs, Map<String, Object> properties) {
        model.Client client = (model.Client) super.createClient(clientType, redirectURIs, properties);
        if (client.convertProperties() && client.convertRedirectUris()) {
            return client;
        } else {
            throw new JSONException("Cannot convert Client data");
        }
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
        model.Client client = clientDAO.findById(clientId);
        if (client.convertProperties() && client.convertRedirectUris()) {
            return client;
        } else {
            throw new JSONException("Cannot convert Client data");
        }
    }

}
