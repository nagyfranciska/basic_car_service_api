package service;

import com.google.inject.Inject;
import dao.UserDAO;
import javassist.NotFoundException;
import model.CDUser;
import org.restlet.Response;
import org.restlet.data.Status;

import java.util.List;

public class UserService {

    @Inject
    UserDAO userDAO;

    @Inject
    GarageService garageService;

    @Inject
    CustomerService customerService;

    public UserService() {}

    public List<CDUser> getUsers() {
        List<CDUser> userList = userDAO.findAll();
        if (userList.isEmpty()) {
            Response.getCurrent().setStatus(Status.SUCCESS_NO_CONTENT);
            return null;
        } else {
            return userList;
        }
    }

    public List<CDUser> getUsersByCustomer(Integer customerId) {
        List<CDUser> userList = userDAO.findAllByCustomer(customerId);
        if (userList.isEmpty()) {
            Response.getCurrent().setStatus(Status.SUCCESS_NO_CONTENT);
            return null;
        } else {
            return userList;
        }
    }

    public List<CDUser> getUsersByGarage(Integer garageId) {
        List<CDUser> userList = userDAO.findAllByGarage(garageId);
        if (userList.isEmpty()) {
            Response.getCurrent().setStatus(Status.SUCCESS_NO_CONTENT);
            return null;
        } else {
            return userList;
        }
    }

    //TODO: postman gives it back even is it belongs to customers and we change url to garages (only considers the id, not the belongings)
    public CDUser getUserById(Integer userId){
        CDUser user = userDAO.findById(userId);
        if (user != null) {
            return user;
        } else {
            try {
                throw new NotFoundException("User not found");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        } return null;
    }

    public CDUser saveUserToGarage(CDUser user, Integer garageId) {
        user.setGarage(garageService.getGarageById(garageId));
        Response.getCurrent().setStatus(Status.SUCCESS_CREATED);
        return userDAO.save(user);
    }

    public CDUser saveUserToCustomer(CDUser user, Integer customerId) {
        user.setCustomer(customerService.getCustomerById(customerId));
        Response.getCurrent().setStatus(Status.SUCCESS_CREATED);
        return userDAO.save(user);
    }

    public CDUser updateUser(CDUser user, Integer userId) {
        return userDAO.update(user, userId);
    }

    //TODO : fix exception handling
    public CDUser deleteUser(Integer userId){
        CDUser user = userDAO.delete(userId);
        if (user != null) {
            return user;
        } else {
            try {
                throw new NotFoundException("User not found");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        } return null;
    }
}
