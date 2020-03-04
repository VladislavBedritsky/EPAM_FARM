package org.example.EPAM_FARM.service.impl;

import org.example.EPAM_FARM.dao.UserDao;
import org.example.EPAM_FARM.model.Role;
import org.example.EPAM_FARM.model.User;
import org.example.EPAM_FARM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

}
