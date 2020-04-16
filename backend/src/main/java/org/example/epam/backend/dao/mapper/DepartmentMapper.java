package org.example.epam.backend.dao.mapper;

import org.example.epam.backend.model.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RowMapper interface implementation
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
public class DepartmentMapper implements RowMapper<Department> {

    @Override
    public Department mapRow(ResultSet resultSet, int i) throws SQLException {

        Department department = new Department();
        department.setId(resultSet.getInt("id"));
        department.setName(resultSet.getString("name"));

        return department;
    }
}

