package org.example.oauth.mapper;

import org.example.oauth.model.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {

        return Role.valueOf(resultSet.getString("r.role"));
    }
}