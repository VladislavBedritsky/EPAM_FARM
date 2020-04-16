package org.example.epam.backend.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class EmployeeTest {

    private static final Integer EMPLOYEE_ID = 1;
    private static final String EMPLOYEE_NAME = "Jack Wolf";
    private static final LocalDate EMPLOYEE_BIRTHDAY = LocalDate.of(2014, Month.APRIL,1);
    private static final Float EMPLOYEE_SALARY = 15003.1234F;
    private static final Department DEPARTMENT = new Department(1,"Marketing");
    private Employee employee;

    @Before
    public void setEmployee() {
        employee = new Employee();
    }

    @Test
    public void getId() {
        employee.setId(1);

        Assert.assertNotNull(employee);
        Assert.assertEquals(employee.getId(), EMPLOYEE_ID);
    }

    @Test
    public void getName() {
        employee.setName("Jack Wolf");

        Assert.assertNotNull(employee);
        Assert.assertEquals(employee.getName(), EMPLOYEE_NAME);
    }

    @Test
    public void getBirthday() {
        employee.setBirthday(LocalDate.of(2014, Month.APRIL,1));

        Assert.assertNotNull(employee);
        Assert.assertEquals(employee.getBirthday(), EMPLOYEE_BIRTHDAY);
    }

    @Test
    public void getSalary() {
        employee.setSalary(15003.1234F);

        Assert.assertNotNull(employee);
        Assert.assertEquals(employee.getSalary(), EMPLOYEE_SALARY);
    }

    @Test
    public void getDepartment() {
        Department department = new Department(1,"Marketing");
        employee.setDepartment(department);

        Assert.assertNotNull(employee);
        Assert.assertEquals(employee.getDepartment(), DEPARTMENT);
    }

}