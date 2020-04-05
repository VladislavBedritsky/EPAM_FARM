package org.example.EPAM_FARM.backend.dao.impl;

import org.example.EPAM_FARM.backend.dao.DepartmentDao;
import org.example.EPAM_FARM.backend.dao.mapper.DepartmentMapper;
import org.example.EPAM_FARM.backend.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@PropertySource("classpath:sql_department.properties")
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${department.findAll}")
    private String FIND_ALL;

    @Value("${department.findById}")
    private String FIND_BY_ID;

    @Value("${department.saveDepartment}")
    private String SAVE_DEPARTMENT;

    @Value("${department.deleteDepartment}")
    private String DELETE_DEPARTMENT;

    @Value("${department.updateDepartment}")
    private String UPDATE_DEPARTMENT;

    @Value("${department.getAverageSalary}")
    private String GET_AVERAGE_SALARY;

    @Value("${department.setForeignKeys}")
    private String SET_FOREIGN_KEYS;


    @Override
    public List<Department> findAll() {
        return jdbcTemplate.query(FIND_ALL, new DepartmentMapper());
    }

    @Override
    public Department findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new DepartmentMapper(), id);
    }

    @Override
    public void saveDepartment(Department department) {
        jdbcTemplate.update(SAVE_DEPARTMENT, department.getName());
    }

    @Override
    public void deleteDepartment(Integer id) {
        jdbcTemplate.update(SET_FOREIGN_KEYS,0);
        jdbcTemplate.update(DELETE_DEPARTMENT, id);
        jdbcTemplate.update(SET_FOREIGN_KEYS,1);
    }

    @Override
    public void updateDepartment(Integer id, String name) {
        jdbcTemplate.update(UPDATE_DEPARTMENT,name,id);
    }

    @Override
    public Float getAverageSalaryInDepartment(Integer id) {
        return jdbcTemplate.queryForObject(GET_AVERAGE_SALARY, Float.class, id);
    }

}