package org.example.epam.backend.dao.mapper;

import org.example.epam.backend.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is responsible for mapping fields from db
 * on Employee object exclude Department field
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
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
