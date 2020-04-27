package pl.website.service;

import pl.website.dao.DAOFactory;
import pl.website.dao.UserDAO;
import pl.website.model.User;

public class UserService {

    public void addUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setActive(true);
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDAO = factory.getUserDAO();
        userDAO.create(user);
    }
}
