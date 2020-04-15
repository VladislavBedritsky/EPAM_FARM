package org.example.EPAM_FARM.backend.service.impl;

import org.example.EPAM_FARM.backend.model.Department;
import org.example.EPAM_FARM.backend.service.DepartmentService;
import org.junit.Assert;
import org.junit.Before;
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

    private Department getLast;

    @Before
    public void setUp() {
        getLast = departmentService.findAll().stream().reduce((first, second) -> second).orElse(null);
    }

    @Test
    public void findAll() {
        List<Department> departments = departmentService.findAll();
        Assert.assertNotNull(departments);
    }

    @Test
    public void isDepartmentNameAlreadyExists() {
        Assert.assertTrue(departmentService.isDepartmentNameAlreadyExists(getLast.getName()));
    }

    @Test
    public void findDepartmentIdByDepartmentName() {
        Assert.assertNotNull(departmentService.findDepartmentIdByDepartmentName(getLast.getName()));
    }

    @Test
    public void getAverageSalaryInDepartment() {
        Assert.assertNotNull(departmentService.getAverageSalaryInDepartment(getLast.getId()));
    }

}