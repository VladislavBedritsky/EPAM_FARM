package org.example.epam.backend.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.epam.backend.dao.DepartmentDao;
import org.example.epam.backend.model.Department;
import org.example.epam.backend.dao.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * DepartmentDao interface implementation
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@Repository
@PropertySource("classpath:sql_department.properties")
public class DepartmentDaoImpl implements DepartmentDao {

    private static Logger LOGGER = LogManager.getLogger(DepartmentDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${department.findAll}")
    private String findAll;
    @Value("${department.findById}")
    private String findById;
    @Value("${department.saveDepartment}")
    private String saveDepartment;
    @Value("${department.deleteDepartment}")
    private String deleteDepartment;
    @Value("${department.updateDepartment}")
    private String updateDepartment;
    @Value("${department.getAverageSalary}")
    private String getAverageSalary;
    @Value("${department.setForeignKeys}")
    private String setForeignKeys;

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        try {
            departments = jdbcTemplate.query(
                    findAll,
                    new DepartmentMapper());

        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e);
        }
        return departments;
    }

    @Override
    public Department findById(Integer id) {
        Department department = new Department();
        try {
            department = jdbcTemplate.queryForObject(
                    findById,
                    new DepartmentMapper(),
                    id);

        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e);
        }

        return department;
    }

    @Override
    public void saveDepartment(Department department) {
        jdbcTemplate.update(saveDepartment, department.getName());
    }

    @Override
    public void deleteDepartment(Integer id) {
        jdbcTemplate.update(setForeignKeys,0);
        jdbcTemplate.update(deleteDepartment, id);
        jdbcTemplate.update(setForeignKeys,1);
    }

    @Override
    public void updateDepartment(Integer id, String name) {
        jdbcTemplate.update(updateDepartment, name, id);
    }

    @Override
    public Float getAverageSalaryInDepartment(Integer id) {
        Float averageSalary = null;
        try {
            averageSalary = jdbcTemplate.queryForObject(
                    getAverageSalary,
                    Float.class,
                    id);

        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e);
        }
        return averageSalary;
    }

}
