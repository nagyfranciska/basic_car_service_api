package service.model;

import com.google.inject.Inject;
import dao.UserDAO;
import exception.general.CDNotFoundException;
import model.CDUser;
import org.restlet.Response;
import org.restlet.data.Status;

import java.util.List;

public class UserService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private GarageService garageService;

    @Inject
    private CustomerService customerService;

    public UserService() {
    }

    public List<CDUser> getUsersByCustomer(Integer userId) {
        List<CDUser> userList = userDAO.findAllByCustomer(userId);
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

    //TODO: Save and update methods may be refactored with `if` statement to check for success
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

    public CDUser getUserById(Integer userId) {
        CDUser user = userDAO.findById(userId);
        if (user != null) {
            return user;
        } else {
            throw new CDNotFoundException("User");
        }
    }

    public CDUser updateUser(CDUser user, Integer userId) {
        return userDAO.update(user, userId);
    }

    public CDUser deleteUser(Integer userId) {
        CDUser user = userDAO.delete(userId);
        if (user != null) {
            return user;
        } else {
            throw new CDNotFoundException("User");
        }
    }

    public CDUser getUserByUsername(String username) {
        CDUser user = userDAO.getByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new CDNotFoundException("User");
        }
    }

}
