package org.example.EPAM_FARM.service.impl;

import org.example.EPAM_FARM.model.Employee;
import org.example.EPAM_FARM.service.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
public class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void findAll() {
        List<Employee> employees = employeeService.findAll();
        Assert.assertNotNull(employees);
    }

}