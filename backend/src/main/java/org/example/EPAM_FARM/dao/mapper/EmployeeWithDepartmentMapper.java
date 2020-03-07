package org.example.EPAM_FARM.dao.mapper;

import org.example.EPAM_FARM.model.Department;
import org.example.EPAM_FARM.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeWithDepartmentMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {

        Employee employee = new Employee();
        employee.setId(resultSet.getInt("em_id"));
        employee.setName(resultSet.getString("em_name"));
        employee.setBirthday(resultSet.getDate("em_birthday").toLocalDate());
        employee.setSalary(resultSet.getFloat("em_salary"));

        Department department = new Department();
        department.setId(resultSet.getInt("d_id"));
        department.setName(resultSet.getString("d_name"));

        employee.setDepartment(department);

        return employee;
    }
}
