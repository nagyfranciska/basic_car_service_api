package service;

import org.restlet.ext.oauth.internal.AbstractClientManager;
import org.restlet.ext.oauth.internal.Client;

import java.util.Map;

public class CarClientManager extends AbstractClientManager {


    @Override
    protected Client createClient(String s, char[] chars, Client.ClientType clientType, String[] strings, Map<String, Object> map) {
        return null;
    }

    @Override
    public void deleteClient(String s) {

    }

    @Override
    public Client findById(String s) {
        return null;
    }
}
