package org.example.EPAM_FARM.dao.impl;

import org.example.EPAM_FARM.dao.mapper.DepartmentMapper;
import org.example.EPAM_FARM.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.example.EPAM_FARM.dao.DepartmentDao;

import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Department> findAll() {
        String sql = "SELECT * FROM departments";
        return jdbcTemplate.query(sql, new DepartmentMapper());
    }
}
