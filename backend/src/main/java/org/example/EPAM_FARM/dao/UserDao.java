package org.example.EPAM_FARM.dao;

import org.example.EPAM_FARM.model.Role;
import org.example.EPAM_FARM.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll ();

    User findByUsername(String username);

    List<Role> findUserRolesByUsername(String username);
}
