package org.example.epamFarm.backend.dao.impl;

import org.example.epamFarm.backend.dao.UserDao;
import org.example.epamFarm.backend.dao.mapper.UserMapper;
import org.example.epamFarm.backend.dao.mapper.UserRoleMapper;
import org.example.epamFarm.backend.model.Role;
import org.example.epamFarm.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@PropertySource("classpath:sql_user.properties")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${user.findByUsername}")
    private String FIND_BY_USERNAME;
    @Value("${user.findAll}")
    private String FIND_ALL;
    @Value("${user.findUserRolesByUsername}")
    private String FIND_USER_ROLES_BY_USERNAME;
    @Value("${user.saveUser}")
    private String SAVE_USER;
    @Value("${user.saveUserRole}")
    private String SAVE_USER_ROLE;

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL, new UserMapper());
    }

    @Override
    public User findByUsername(String username) {
        return jdbcTemplate.queryForObject(FIND_BY_USERNAME, new UserMapper(), username);
    }

    @Override
    public List<Role> findUserRolesByUsername(String username) {
        return jdbcTemplate.query(FIND_USER_ROLES_BY_USERNAME, new UserRoleMapper(),username);
    }

    @Override
    public void saveUser(User registrationUser) {
        jdbcTemplate.update(
                SAVE_USER,
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
                SAVE_USER_ROLE,
                userId,
                "USER"
        );
    }
}
