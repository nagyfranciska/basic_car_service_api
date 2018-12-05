package service;

import com.google.inject.Inject;
import dao.UserDAO;
import javassist.NotFoundException;
import model.User;
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

    public List<User> getUsers() {
        List<User> userList = userDAO.findAll();
        if (userList.isEmpty()) {
            Response.getCurrent().setStatus(Status.SUCCESS_NO_CONTENT);
            return null;
        } else {
            return userList;
        }
    }

    public List<User> getUsersByCustomer(Integer customerId) {
        List<User> userList = userDAO.findAllByCustomer(customerId);
        if (userList.isEmpty()) {
            Response.getCurrent().setStatus(Status.SUCCESS_NO_CONTENT);
            return null;
        } else {
            return userList;
        }
    }

    public List<User> getUsersByGarage(Integer garageId) {
        List<User> userList = userDAO.findAllByGarage(garageId);
        if (userList.isEmpty()) {
            Response.getCurrent().setStatus(Status.SUCCESS_NO_CONTENT);
            return null;
        } else {
            return userList;
        }
    }

    //TODO: postman gives it back even is it belongs to customers and we change url to garages (only considers the id, not the belongings)
    public User getUserById(Integer userId){
        User user = userDAO.findById(userId);
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

    public User saveUserToGarage(User user, Integer garageId) {
        user.setGarage(garageService.getGarageById(garageId));
        Response.getCurrent().setStatus(Status.SUCCESS_CREATED);
        return userDAO.save(user);
    }

    public User saveUserToCustomer(User user, Integer customerId) {
        user.setCustomer(customerService.getCustomerById(customerId));
        Response.getCurrent().setStatus(Status.SUCCESS_CREATED);
        return userDAO.save(user);
    }

    public User updateUser(User user, Integer userId) {
        return userDAO.update(user, userId);
    }

    //TODO : fix exception handling
    public User deleteUser(Integer userId){
        User user = userDAO.delete(userId);
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
