package org.example.epam.backend.service;

import org.example.epam.backend.model.Role;
import org.example.epam.backend.model.Session;
import org.example.epam.backend.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;

import java.util.List;

/**
 * interface UserService
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
public interface UserService extends UserDetailsService {

    /**
     * Get all users
     *
     * @return list of users
     */
    List<User> findAll ();

    /**
     * Get UserDetails with specified user's username.
     *
     * @param username User's username
     * @return UserDetails
     */
    UserDetails loadUserByUsername(String username);

    /**
     * Get user roles with specified user's username.
     *
     * @param username User's username
     * @return list of roles
     */
    List<Role> findUserRolesByUsername(String username);

    /**
     * Check if user has role ADMIN.
     *
     * @param authentication Authentication user
     * @return boolean
     */
    boolean isUserHasAdminRole(Authentication authentication);

    /**
     * Check if user authenticated and has role ADMIN in LDAP data store and DB,
     * when add attributes to Model.
     *
     * @param model Model
     */
    void checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel(Model model);

    /**
     * Check if user has role ADMIN.
     *
     * @param authentication Authentication user
     * @return boolean
     */
    boolean isUserFromLdapHasAdminRole(Authentication authentication);

    /**
     * Save user.
     *
     * @param registrationUser User
     */
    boolean saveUser(User registrationUser);

    void saveSession(Session session);
}
