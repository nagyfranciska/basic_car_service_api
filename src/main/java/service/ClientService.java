package service;

import com.google.inject.Inject;
import dao.ClientDAO;
import model.Client;

import java.util.List;

public class ClientService {

    @Inject
    ClientDAO clientDAO;

   public List<Client> getAllClients() {
       return clientDAO.findAll();
   }

   public Client saveClient(Client client) {
       return clientDAO.save(client);
   }

   public Client getClientById(String id){
       return clientDAO.findById(id);
   }

   public Client deleteClient(String id) {
       return clientDAO.delete(id);
   }
}
