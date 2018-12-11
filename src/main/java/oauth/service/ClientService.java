package oauth.service;

import com.google.inject.Inject;
import oauth.dao.ClientDAO;
import oauth.model.Client;
import org.restlet.ext.oauth.internal.ClientManager;

import java.util.List;
import java.util.Map;

public class ClientService implements ClientManager {

    @Inject
    ClientDAO clientDAO;

    @Override
    public org.restlet.ext.oauth.internal.Client createClient(org.restlet.ext.oauth.internal.Client.ClientType clientType,
                                                              String[] redirectURIs, Map<String, Object> properties) {
        return clientDAO.save(new Client(clientType, redirectURIs, properties ));
    }

    public void deleteClient(String s) {
        clientDAO.delete(s);
    }

    public Client findById(String id) {
        return clientDAO.findById(id);
    }

    public List<Client> findAll() {
        return clientDAO.findAll();
    }
}
