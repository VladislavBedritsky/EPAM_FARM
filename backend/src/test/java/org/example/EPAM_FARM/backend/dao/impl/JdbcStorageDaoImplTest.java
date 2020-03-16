package org.example.EPAM_FARM.backend.dao.impl;

import org.example.EPAM_FARM.backend.dao.DepartmentDao;
import org.example.EPAM_FARM.backend.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
public class JdbcStorageDaoImplTest {

    @Autowired
    private DepartmentDao jdbcStorageDao;

    private Department getLast;
    private List<Department> departments;


    @Test
    public void findAll() {
        Assert.assertNotNull(jdbcStorageDao.findAll());
    }

    @Test
    public void findById() {
        Department department = jdbcStorageDao.findById(1);
        Assert.assertNotNull(department);
    }

}