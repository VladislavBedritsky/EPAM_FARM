package org.example.epam.backend.dao.mapper;

import org.example.epam.backend.model.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RowMapper interface implementation
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
public class UserRoleMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {

        return Role.valueOf(resultSet.getString("r.role"));
    }
}
