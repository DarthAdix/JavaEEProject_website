package pl.website.dao;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.website.model.User;
import pl.website.util.ConnectionProvider;

import java.util.List;
import java.util.Objects;

public class UserDAOImpl implements UserDAO {

    private static final String CREATE_USER =
            "INSERT INTO user(username, email, password, is_active) VALUES(:username, :email, :password, :active);";

    private NamedParameterJdbcTemplate template;

    public UserDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDS());
    }

    @Override
    public User create(User user) {
        User resultUser = new User(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword());
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
        int update = template.update(CREATE_USER, parameterSource, holder);
        if (update > 0) {
            resultUser.setId(Objects.requireNonNull(holder.getKey()).longValue());
            setPrivileges(resultUser);
        }
        return resultUser;
    }

    private void setPrivileges(User user) {
        final String userRoleQuery = "INSERT INTO user_role(username) VALUES(:username);";
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
        template.update(userRoleQuery, parameterSource);
    }

    @Override
    public User read(Long primaryKey) {
        return null;
    }

    @Override
    public boolean update(User updateObject) {
        return false;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }
}
