package org.example.epam.backend.service.impl;

import org.example.epam.backend.model.Employee;
import org.example.epam.backend.service.EmployeeService;
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
public class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService employeeService;

    private Employee getLast;

    @Before
    public void setUp() {
        getLast = employeeService.findAll().stream().reduce((first,second) -> second).orElse(null);
    }

    @Test
    public void findAll() {
        List<Employee> employees = employeeService.findAll();
        Assert.assertNotNull(employees);
    }

    @Test
    public void isEmployeeNameAlreadyExists() {
        Assert.assertTrue(employeeService.isEmployeeNameAlreadyExists(getLast.getName()));
    }

    @Test
    public void isProperFloatValue() {
        String salary = "102.12";

        Assert.assertTrue(employeeService.isProperFloatValue(salary));
    }
    @Test
    public void isProperFloatValueIfFalse() {
        String salary1 = "102,12";
        String salary2 = "asfd";

        Assert.assertFalse(employeeService.isProperFloatValue(salary1));
        Assert.assertFalse(employeeService.isProperFloatValue(salary2));
    }

}