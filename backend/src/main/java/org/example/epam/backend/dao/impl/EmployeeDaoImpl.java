package org.example.epam.backend.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.epam.backend.dao.EmployeeDao;
import org.example.epam.backend.model.Employee;
import org.example.epam.backend.dao.mapper.EmployeeWithDepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * EmployeeDao interface implementation
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@Repository
@PropertySource("classpath:sql_employee.properties")
public class EmployeeDaoImpl implements EmployeeDao {

    private static Logger LOGGER = LogManager.getLogger(EmployeeDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${employee.findAll}")
    private String findAll;
    @Value("${employee.findById}")
    private String findById;
    @Value("${employee.findEmployeesByDepartmentId}")
    private String findEmployeesByDepartmentId;
    @Value("${employee.saveEmployee}")
    private String saveEmployee;
    @Value("${employee.deleteEmployee}")
    private String deleteEmployee;
    @Value("${employee.updateDepartment}")
    private String updateEmployee;
    @Value("${employee.saveEmployeeForRestController}")
    private String saveEmployeeForRest;
    @Value("${employee.updateEmployeeForRestController}")
    private String updateEmployeeForRest;
    @Value("${employee.findEmployeeByName}")
    private String findEmployeeByName;

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(findAll, new EmployeeWithDepartmentMapper());
    }

    @Override
    public Employee findById(Integer id) {
        return jdbcTemplate.queryForObject(findById,new EmployeeWithDepartmentMapper(),id);
    }

    @Override
    public List<Employee> findEmployeesByDepartmentId(Integer id) {
        return jdbcTemplate.query(findEmployeesByDepartmentId, new EmployeeWithDepartmentMapper(), id);
    }

    @Override
    public void saveEmployee(Employee employee, Integer departmentId) {
        jdbcTemplate.update(
                saveEmployee,
                employee.getName(),
                employee.getBirthday(),
                employee.getSalary(),
                departmentId
        );
    }

    @Override
    public void deleteEmployee(Integer id) {
        jdbcTemplate.update(deleteEmployee, id);
    }

    @Override
    public void updateEmployee(Integer employeeId, Employee employee, Integer departmentId) {
        jdbcTemplate.update(
                updateEmployee,
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
                saveEmployeeForRest,
                employee.getName(),
                employee.getBirthday(),
                employee.getSalary()
        );
    }

    @Override
    public void updateEmployeeForRestController(Employee employee, Integer id) {
        jdbcTemplate.update(
                updateEmployeeForRest,
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
            employee = jdbcTemplate.queryForObject(findEmployeeByName, new EmployeeWithDepartmentMapper(), name);
        }catch (EmptyResultDataAccessException e) {
            LOGGER.error(e);
        }
        return employee;
    }

}
