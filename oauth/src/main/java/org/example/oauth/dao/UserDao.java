package org.example.oauth.dao;

import org.example.oauth.mapper.UserMapper;
import org.example.oauth.mapper.UserRoleMapper;
import org.example.oauth.model.Role;
import org.example.oauth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * class UserDao
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@Repository
public class UserDao {

    private static final String FIND_BY_USERNAME =
            "SELECT * FROM users WHERE username=?";
    private static final String FIND_ROLES_BY_USERNAME =
            "SELECT r.role  FROM users u JOIN roles r ON u.id=r.user_id WHERE u.username=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Get user with specified user's username.
     *
     * @param username User's username
     * @return user
     */
    public User findByUserName(String username) {
        return jdbcTemplate.queryForObject(
                FIND_BY_USERNAME,
                new UserMapper(),
                username);
    }

    /**
     * Get user roles with specified user's username.
     *
     * @param username User's username
     * @return list of roles
     */
    public List<Role> findUserRolesByUsername(String username) {
        return jdbcTemplate.query(
                FIND_ROLES_BY_USERNAME,
                new UserRoleMapper(),
                username);
    }
}