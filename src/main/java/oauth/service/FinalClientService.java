package oauth.service;

import com.google.inject.Inject;
import oauth.dao.FinalClientDAO;
import oauth.model.FinalClient;

public class FinalClientService {

    @Inject
    FinalClientDAO clientDAO;

    public FinalClient saveClient(FinalClient client) {
        return clientDAO.save(client);
    }

    public FinalClient getById(String id) {
        return clientDAO.findById(id);
    }
}
