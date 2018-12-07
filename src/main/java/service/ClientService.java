package service;

import com.google.inject.Inject;
import dao.ClientDAO;
import model.Client;
import org.restlet.ext.oauth.internal.AbstractClientManager;

import java.util.Map;

public class ClientService extends AbstractClientManager {

    @Inject
    ClientDAO clientDAO;

    @Override
    public void deleteClient(String s) {
        clientDAO.delete(s);
    }

    @Override
    public org.restlet.ext.oauth.internal.Client findById(String s) {
        return clientDAO.findById(s);
    }

    @Override
    protected org.restlet.ext.oauth.internal.Client createClient(String s, char[] chars, org.restlet.ext.oauth.internal.Client.ClientType clientType, String[] strings, Map<String, Object> map) {
        Client client = new Client(chars, clientType);
        return clientDAO.save(client);
    }
}
