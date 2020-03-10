package org.example.EPAM_FARM.service;

import org.example.EPAM_FARM.model.Role;
import org.example.EPAM_FARM.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAll ();

    UserDetails loadUserByUsername(String username);

    List<Role> findUserRolesByUsername(String username);

    boolean isUserHasAdminRole(String username);

    void checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel(Model model, User user);

    boolean isUserFromLdapHasAdminRole(Authentication authentication);

    void checkIfUserIsNotAnonymous(Model model);
}
