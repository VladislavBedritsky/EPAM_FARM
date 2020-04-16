package org.example.epam.backend.service;

import org.example.epam.backend.model.Role;
import org.example.epam.backend.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAll ();

    UserDetails loadUserByUsername(String username);

    List<Role> findUserRolesByUsername(String username);

    boolean isUserHasAdminRole(Authentication authentication);

    void checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel(Model model);

    boolean isUserFromLdapHasAdminRole(Authentication authentication);

    boolean saveUser(User registrationUser);
}
