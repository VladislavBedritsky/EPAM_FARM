package org.example.EPAM_FARM.backend.service.impl;

import org.example.EPAM_FARM.backend.dao.UserDao;
import org.example.EPAM_FARM.backend.model.Role;
import org.example.EPAM_FARM.backend.model.User;
import org.example.EPAM_FARM.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
    public boolean isUserHasAdminRole(Authentication authentication) {

        for (int i = 0; i < authentication.getAuthorities().size(); i++) {
            if (authentication.getAuthorities().toArray()[i].toString().equalsIgnoreCase(Role.ADMIN.toString())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<User> findAll () {
        return userDao.findAll();
    }

    @Override
    public void checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("isAdmin", isUserHasAdminRole(authentication));
        if (!authentication.getName().equals("anonymousUser")) {
            model.addAttribute("authUser", true);
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
    public boolean saveUser(User registrationUser) {

        try {
            User userFromDB = userDao.findByUsername(registrationUser.getUsername());
        } catch (EmptyResultDataAccessException e) {
            registrationUser.setPassword(BCrypt.hashpw(registrationUser.getPassword(),BCrypt.gensalt(12)));
            registrationUser.setActive(true);
            userDao.saveUser(registrationUser);
            userDao.setUserRole(getLastUserId());

            return true;
        }

        return false;
    }

    public Integer getLastUserId() {
        User getLast = userDao.findAll().stream().reduce((first, second) -> second).orElse(null);
        return getLast.getId();
    }

}
