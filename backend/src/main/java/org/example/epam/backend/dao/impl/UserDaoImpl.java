package org.example.epam.backend.dao.impl;

import org.example.epam.backend.dao.UserDao;
import org.example.epam.backend.dao.mapper.UserMapper;
import org.example.epam.backend.dao.mapper.UserRoleMapper;
import org.example.epam.backend.model.Role;
import org.example.epam.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(findAll, new UserMapper());
    }

    @Override
    public User findByUsername(String username) {
        return jdbcTemplate.queryForObject(findByUsername, new UserMapper(), username);
    }

    @Override
    public List<Role> findUserRolesByUsername(String username) {
        return jdbcTemplate.query(findUserRolesByUsername, new UserRoleMapper(),username);
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
}
