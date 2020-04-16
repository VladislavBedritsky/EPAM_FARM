package org.example.epam.backend.dao.impl;

import org.example.epam.backend.dao.DepartmentDao;
import org.example.epam.backend.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
public class JdbcStorageDaoImplTest {

    @Autowired
    private DepartmentDao jdbcStorageDao;

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