package org.example.oauth.service;

import org.example.oauth.dao.UserDao;
import org.example.oauth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService interface implementation
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    /**
     * Get UserDetails with specified user's username.
     *
     * @param s User's username
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByUserName(s);
        user.setRoles(userDao.findUserRolesByUsername(s));
        return (UserDetails) user;
    }
}
