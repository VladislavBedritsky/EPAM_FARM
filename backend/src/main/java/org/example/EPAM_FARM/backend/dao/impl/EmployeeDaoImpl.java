package org.example.EPAM_FARM.backend.dao.impl;

import org.example.EPAM_FARM.backend.dao.EmployeeDao;
import org.example.EPAM_FARM.backend.dao.mapper.EmployeeWithDepartmentMapper;
import org.example.EPAM_FARM.backend.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Value("${employee.saveEmployeeForRestController}")
    private String SAVE_EMPLOYEE_FOR_REST;

    @Value("${employee.updateEmployeeForRestController}")
    private String UPDATE_EMPLOYEE_FOR_REST;

    @Value("${employee.findEmployeeByName}")
    private String FIND_EMPLOYEE_BY_NAME;

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(FIND_ALL, new EmployeeWithDepartmentMapper());
    }

    @Override
    public Employee findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID,new EmployeeWithDepartmentMapper(),id);
    }

    @Override
    public List<Employee> findEmployeesByDepartmentId(Integer id) {
        return jdbcTemplate.query(FIND_EMPLOYEES_BY_DEPARTMENT_ID, new EmployeeWithDepartmentMapper(), id);
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
                employee.getBirthday(),
                employee.getSalary(),
                departmentId,
                employeeId
        );
    }

    @Override
    public void saveEmployeeForRestController(Employee employee) {
        jdbcTemplate.update(
                SAVE_EMPLOYEE_FOR_REST,
                employee.getName(),
                employee.getBirthday(),
                employee.getSalary()
        );
    }

    @Override
    public void updateEmployeeForRestController(Employee employee, Integer id) {
        jdbcTemplate.update(
                UPDATE_EMPLOYEE_FOR_REST,
                employee.getName(),
                employee.getBirthday(),
                employee.getSalary(),
                id
        );
    }

    @Override
    public Employee findEmployeeByName(String name) {
        Employee employee = new Employee();
        try{
            employee = jdbcTemplate.queryForObject(FIND_EMPLOYEE_BY_NAME, new EmployeeWithDepartmentMapper(), name);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return employee;
    }
}
