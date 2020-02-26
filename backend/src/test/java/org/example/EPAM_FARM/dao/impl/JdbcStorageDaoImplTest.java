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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
public class JdbcStorageDaoImplTest {

    @Autowired
    private DepartmentDao jdbcStorageDao;

    private Department getLast;

    @Before
    public void setUp() {
        getLast = jdbcStorageDao.findAll().stream().reduce((first,second) -> second).orElse(null);
    }

    @Test
    public void findAll() {
        List<Department> departments = jdbcStorageDao.findAll();
        Assert.assertNotNull(departments);
    }

    @Test
    public void findById() {
        Department department = jdbcStorageDao.findById(1);
        Assert.assertNotNull(department);
    }

    @Test
    public void checkSaveUpdateDeleteDepartment() {
        Department department = new Department();
        department.setName("lastNameDepartment");
        jdbcStorageDao.saveDepartment(department);

        Department lastAfterSave = jdbcStorageDao.findAll().stream().reduce((first,second) -> second).orElse(null);
        Assert.assertNotNull(lastAfterSave);
        Assert.assertEquals(lastAfterSave.getName(), department.getName());

        jdbcStorageDao.updateDepartment(lastAfterSave.getId(), "newLastNameDepartment");
        Department lastAfterUpdate = jdbcStorageDao.findAll().stream().reduce((first,second) -> second).orElse(null);
        Assert.assertNotNull(lastAfterUpdate);
        Assert.assertEquals(lastAfterUpdate.getName(), "newLastNameDepartment");

        jdbcStorageDao.deleteDepartment(lastAfterUpdate.getId());
        Assert.assertNotEquals(lastAfterUpdate.getName(),getLast.getName());
        Assert.assertNotEquals(lastAfterUpdate.getId(),getLast.getId());
    }
}