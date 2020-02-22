package org.example.EPAM_FARM.dao.impl;

import org.example.EPAM_FARM.dao.DepartmentDao;
import org.example.EPAM_FARM.model.Department;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-dao.xml"})
public class DepartmentDaoImplTest {


    private DepartmentDao departmentDao;

    @Autowired
    private DataSource dataSource;

    @Before
    public void setup() throws Exception {
        departmentDao = new DepartmentDaoImpl(dataSource);
    }



    @Test
    public void findAll() {
        List<Department> departments = departmentDao.findAll();
        Assert.assertNotNull(departments);
     }

}