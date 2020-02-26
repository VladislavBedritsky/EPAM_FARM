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

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(FIND_ALL, new EmployeeMapper());
    }

    @Override
    public Employee findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID,new EmployeeMapper(),id);
    }
}
