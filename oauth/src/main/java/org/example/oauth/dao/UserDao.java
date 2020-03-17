package org.example.oauth.dao;

import org.example.oauth.mapper.UserMapper;
import org.example.oauth.mapper.UserRoleMapper;
import org.example.oauth.model.Role;
import org.example.oauth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findByUserName(String username) {
        String sql = "SELECT * FROM users WHERE username=?";
        return jdbcTemplate.queryForObject(sql,new UserMapper(),username);
    }

    public List<Role> findUserRolesByUsername(String username) {
        String sql = "SELECT r.role  FROM users u JOIN roles r ON u.id=r.user_id WHERE u.username=? ";
        return jdbcTemplate.query(sql, new UserRoleMapper(),username);
    }
}