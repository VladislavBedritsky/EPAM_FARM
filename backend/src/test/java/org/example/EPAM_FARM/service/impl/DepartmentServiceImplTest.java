package org.example.EPAM_FARM.service.impl;

import org.example.EPAM_FARM.model.Department;
import org.example.EPAM_FARM.service.DepartmentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
public class DepartmentServiceImplTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void findAll() {
        List<Department> departments = departmentService.findAll();
        Assert.assertNotNull(departments);
    }

}