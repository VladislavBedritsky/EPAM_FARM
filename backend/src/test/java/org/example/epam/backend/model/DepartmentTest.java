package org.example.epam.backend.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DepartmentTest {

    private static final Integer DEPARTMENT_ID = 1;
    private static final String DEPARTMENT_NAME = "Marketing";
    private Department department;

    @Before
    public void setDepartment() {
        department = new Department();
    }

    @Test
    public void getId() {

        department.setId(1);

        Assert.assertNotNull(department);
        Assert.assertEquals(DEPARTMENT_ID, department.getId());
    }

    @Test
    public void getName() {

        department.setName("Marketing");

        Assert.assertNotNull(department);
        Assert.assertEquals(DEPARTMENT_NAME, department.getName());
    }
}