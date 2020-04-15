package org.example.epamFarm.backend.service.impl;

import org.example.epamFarm.backend.dao.DepartmentDao;
import org.example.epamFarm.backend.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
public class JdbcStorageServiceImplTest {

    @Autowired
    private DepartmentDao jdbcStorageDao;

    @Test
    public void findAll() {
        List<Department> departments = jdbcStorageDao.findAll();
        Assert.assertNotNull(departments);
    }
}