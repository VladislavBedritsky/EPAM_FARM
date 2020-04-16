package org.example.epam.backend.dao;

import org.example.epam.backend.model.Role;
import org.example.epam.backend.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll ();

    User findByUsername(String username);

    List<Role> findUserRolesByUsername(String username);

    void saveUser(User registrationUser);

    void setUserRole(Integer userId);
}
