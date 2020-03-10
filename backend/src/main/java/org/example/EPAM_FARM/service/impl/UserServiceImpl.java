package org.example.EPAM_FARM.service.impl;

import org.example.EPAM_FARM.dao.UserDao;
import org.example.EPAM_FARM.model.Role;
import org.example.EPAM_FARM.model.User;
import org.example.EPAM_FARM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        user.setRoles(findUserRolesByUsername(username));
        return (UserDetails) user;
    }

    @Override
    public List<Role> findUserRolesByUsername(String username) {
        return userDao.findUserRolesByUsername(username);
    }

    @Override
    public boolean isUserHasAdminRole(String username) {
        return findUserRolesByUsername(username).contains(Role.ADMIN);
    }

    @Override
    public List<User> findAll () {
        return userDao.findAll();
    }

    public void checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel(Model model, User user) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(user != null ) {
            model.addAttribute("isAdmin", isUserHasAdminRole(user.getUsername()));
            model.addAttribute("authUser", user);
        } else {
            model.addAttribute("isAdmin",isUserFromLdapHasAdminRole(authentication));
            model.addAttribute("authUser",true);
        }
    }

    @Override
    public boolean isUserFromLdapHasAdminRole(Authentication authentication) {
        for(Object temp:authentication.getAuthorities()) {
            if (temp.toString().equalsIgnoreCase("ROLE_ADMINS")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void checkIfUserIsNotAnonymous(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.getName().equals("anonymousUser")) {
            model.addAttribute("authUser", true);
        }
    }

}
