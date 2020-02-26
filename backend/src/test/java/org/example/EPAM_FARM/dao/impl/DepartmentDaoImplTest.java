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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
public class DepartmentDaoImplTest {

    @Autowired
    private DepartmentDao departmentDao;

    private Department getLast;

    @Before
    public void setUp() {
        getLast = departmentDao.findAll().stream().reduce((first,second) -> second).orElse(null);
    }

    @Test
    public void findAllDepartments () {
        Assert.assertNotNull(departmentDao.findAll());
    }

    @Test
    public void findById() {
        Department department = departmentDao.findById(1);
        Assert.assertNotNull(department);
    }

    @Test
    public void checkSaveUpdateDeleteDepartment() {
        Department department = new Department();
        department.setName("lastNameDepartment");
        departmentDao.saveDepartment(department);

        Department lastAfterSave = departmentDao.findAll().stream().reduce((first,second) -> second).orElse(null);
        Assert.assertNotNull(lastAfterSave);
        Assert.assertEquals(lastAfterSave.getName(), department.getName());

        departmentDao.updateDepartment(lastAfterSave.getId(), "newLastNameDepartment");
        Department lastAfterUpdate = departmentDao.findAll().stream().reduce((first,second) -> second).orElse(null);
        Assert.assertNotNull(lastAfterUpdate);
        Assert.assertEquals(lastAfterUpdate.getName(), "newLastNameDepartment");

        departmentDao.deleteDepartment(lastAfterUpdate.getId());
        Assert.assertNotEquals(lastAfterUpdate.getName(),getLast.getName());
        Assert.assertNotEquals(lastAfterUpdate.getId(),getLast.getId());
    }

}