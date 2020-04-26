package pl.website.dao;

import pl.website.model.User;

import java.util.List;

public interface UserDAO extends GenericDAO<User, Long> {

    List<User> getAll();
    User getUserByUsername(String username);

}
