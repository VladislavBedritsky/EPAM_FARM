package org.example.epamFarm.backend.dao.mapper;

import org.example.epamFarm.backend.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setName(resultSet.getString("name"));
        employee.setBirthday(resultSet.getDate("birthday").toLocalDate());
        employee.setSalary(resultSet.getFloat("salary"));
        return employee;
    }
}
