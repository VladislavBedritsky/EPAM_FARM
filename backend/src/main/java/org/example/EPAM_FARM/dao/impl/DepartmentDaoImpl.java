package org.example.EPAM_FARM.dao.impl;

import org.example.EPAM_FARM.dao.DepartmentDao;
import org.example.EPAM_FARM.dao.mapper.DepartmentMapper;
import org.example.EPAM_FARM.model.Department;
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

    @Override
    public List<Department> findAll() {
        return jdbcTemplate.query(FIND_ALL, new DepartmentMapper());
    }

}
