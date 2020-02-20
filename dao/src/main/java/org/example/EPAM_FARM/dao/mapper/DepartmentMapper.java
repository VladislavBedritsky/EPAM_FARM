package org.example.EPAM_FARM.dao.mapper;

import org.example.EPAM_FARM.model.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper implements RowMapper<Department> {

    @Override
    public Department mapRow(ResultSet resultSet, int i) throws SQLException {

        Department department = new Department();
        department.setId(resultSet.getInt("id"));
        department.setName(resultSet.getString("name"));

        return department;
    }
}
