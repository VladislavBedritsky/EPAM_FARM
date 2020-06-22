package org.example.epam.backend.dao;

import org.example.epam.backend.model.Role;
import org.example.epam.backend.model.Session;
import org.example.epam.backend.model.User;

import java.util.List;

/**
 * interface UserDao
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
public interface UserDao {

    /**
     * Get all users
     *
     * @return list of users
     */
    List<User> findAll ();

    /**
     * Get user with specified user's username.
     *
     * @param username User's username
     * @return user
     */
    User findByUsername(String username);

    /**
     * Get user roles with specified user's username.
     *
     * @param username User's username
     * @return list of roles
     */
    List<Role> findUserRolesByUsername(String username);

    /**
     * Save user.
     *
     * @param registrationUser User
     */
    void saveUser(User registrationUser);

    /**
     * Update user role with specified user's id.
     *
     * @param userId User's id
     */
    void setUserRole(Integer userId);

    /**
     * Save session.
     *
     * @param session Session
     */
    void saveSession(Session session);
}
