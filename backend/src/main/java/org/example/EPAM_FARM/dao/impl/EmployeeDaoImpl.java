package org.example.EPAM_FARM.dao.impl;

import org.example.EPAM_FARM.dao.EmployeeDao;
import org.example.EPAM_FARM.dao.mapper.EmployeeMapper;
import org.example.EPAM_FARM.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@PropertySource("classpath:sql_employee.properties")
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${employee.findAll}")
    private String FIND_ALL;

    @Value("${employee.findById}")
    private String FIND_BY_ID;

    @Value("${employee.findEmployeesByDepartmentId}")
    private String FIND_EMPLOYEES_BY_DEPARTMENT_ID;

    @Value("${employee.saveEmployee}")
    private String SAVE_EMPLOYEE;

    @Value("${employee.deleteEmployee}")
    private String DELETE_EMPLOYEE;

    @Value("${employee.updateDepartment}")
    private String UPDATE_EMPLOYEE;

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(FIND_ALL, new EmployeeMapper());
    }

    @Override
    public Employee findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID,new EmployeeMapper(),id);
    }

    @Override
    public List<Employee> findEmployeesByDepartmentId(Integer id) {
        return jdbcTemplate.query(FIND_EMPLOYEES_BY_DEPARTMENT_ID, new EmployeeMapper(), id);
    }

    @Override
    public void saveEmployee(Employee employee, Integer departmentId) {
        jdbcTemplate.update(
                SAVE_EMPLOYEE,
                employee.getName(),
                employee.getBirthday(),
                employee.getSalary(),
                departmentId
        );
    }

    @Override
    public void deleteEmployee(Integer id) {
        jdbcTemplate.update(DELETE_EMPLOYEE,id);
    }

    @Override
    public void updateEmployee(Integer employeeId, Employee employee, Integer departmentId) {
        jdbcTemplate.update(
                UPDATE_EMPLOYEE,
                employee.getName(),
                employee.getSalary(),
                employee.getBirthday(),
                departmentId,
                employeeId
        );
    }
}
