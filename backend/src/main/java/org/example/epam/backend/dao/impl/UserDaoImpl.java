package org.example.epam.backend.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.epam.backend.dao.UserDao;
import org.example.epam.backend.dao.mapper.UserMapper;
import org.example.epam.backend.dao.mapper.UserRoleMapper;
import org.example.epam.backend.model.Role;
import org.example.epam.backend.model.Session;
import org.example.epam.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * UserDao interface implementation
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@Repository
@PropertySource("classpath:sql_user.properties")
public class UserDaoImpl implements UserDao {

    private static Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${user.findByUsername}")
    private String findByUsername;
    @Value("${user.findAll}")
    private String findAll;
    @Value("${user.findUserRolesByUsername}")
    private String findUserRolesByUsername;
    @Value("${user.saveUser}")
    private String saveUser;
    @Value("${user.saveUserRole}")
    private String saveUserRole;
    @Value("${session.saveSession}")
    private String saveSession;

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            users = jdbcTemplate.query(findAll, new UserMapper());

        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e);
        }
        return users;
    }

    @Override
    public User findByUsername(String username) {
        User user = new User();
        try {
            user = jdbcTemplate.queryForObject(
                    findByUsername,
                    new UserMapper(),
                    username);

        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public List<Role> findUserRolesByUsername(String username) {
        List<Role> roles = new ArrayList<>();
        try {
            roles = jdbcTemplate.query(
                    findUserRolesByUsername,
                    new UserRoleMapper(),
                    username);

        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e);
        }
        return roles;
    }

    @Override
    public void saveUser(User registrationUser) {
        jdbcTemplate.update(
                saveUser,
                registrationUser.getFirstName(),
                registrationUser.getLastName(),
                registrationUser.getUsername(),
                registrationUser.getPassword(),
                registrationUser.getEmail(),
                registrationUser.getBirthday(),
                registrationUser.isActive()
                );
    }

    @Override
    public void setUserRole(Integer userId) {
        jdbcTemplate.update(
                saveUserRole,
                userId,
                Role.USER.toString()
        );
    }

    @Override
    public void saveSession(Session session) {
        jdbcTemplate.update(saveSession, session.getTime(), session.getPage());
    }
}
