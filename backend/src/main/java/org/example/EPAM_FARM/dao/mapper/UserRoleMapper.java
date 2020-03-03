package org.example.EPAM_FARM.dao.mapper;

import org.example.EPAM_FARM.model.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {

        return Role.valueOf(resultSet.getString("r.role"));
    }
}
