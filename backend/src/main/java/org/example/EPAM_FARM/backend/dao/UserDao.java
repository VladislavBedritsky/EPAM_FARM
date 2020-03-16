package org.example.EPAM_FARM.backend.dao;

import org.example.EPAM_FARM.backend.model.Role;
import org.example.EPAM_FARM.backend.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll ();

    User findByUsername(String username);

    List<Role> findUserRolesByUsername(String username);
}
