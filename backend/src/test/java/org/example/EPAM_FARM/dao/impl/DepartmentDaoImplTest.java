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
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
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
    public void saveDepartment() {
        Department department = new Department();
        department.setName("newDepartment");
        departmentDao.saveDepartment(department);

        Department lastAfterSave = departmentDao.findAll().stream().reduce((first,second) -> second).orElse(null);

        Assert.assertNotNull(lastAfterSave);
        Assert.assertEquals(lastAfterSave.getName(), department.getName());

    }

    @Test
    public void deleteDepartment() {
        int sizeBeforeDelete = departmentDao.findAll().size();
        departmentDao.deleteDepartment(getLast.getId());

        Assert.assertNotEquals(sizeBeforeDelete, departmentDao.findAll().size());
    }

    @Test
    public void updateDepartment() {
        departmentDao.updateDepartment(getLast.getId(), "newDepartment");
        Department lastAfterUpdate = departmentDao.findAll().stream().reduce((first,second) -> second).orElse(null);

        Assert.assertNotNull(lastAfterUpdate);
        Assert.assertNotEquals(lastAfterUpdate.getName(), getLast.getName());
        Assert.assertEquals(lastAfterUpdate.getId(), getLast.getId());
    }

    @Test
    public void getAverageSalary() {
        Assert.assertNotNull(departmentDao.getAverageSalaryInDepartment(1));
    }

}