package org.example.EPAM_FARM.dao.impl;

import org.example.EPAM_FARM.dao.EmployeeDao;
import org.example.EPAM_FARM.dao.mapper.EmployeeMapper;
import org.example.EPAM_FARM.model.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations={"classpath*:test.xml"})
public class EmployeeDaoImplTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Employee employee;

    @Before
    public void setUp() {
        employee = new Employee();
        employee.setName("Employee");
        employee.setSalary(123.01f);
        employee.setBirthday(LocalDate.of(2012, Month.APRIL,12));
    }

    @Test
    public void findAllEmployees() {
        List<Employee> employees = employeeDao.findAll();
        Assert.assertNotNull(employees);
    }

    @Test
    public void findById() {
        Employee getLast = employeeDao.findAll().stream().reduce((first,second) -> second).orElse(null);
        Assert.assertNotNull(getLast);

        Employee employee = employeeDao.findById(getLast.getId());
        Assert.assertNotNull(employee);
    }

    @Test
    public void saveEmployee() {
        employeeDao.saveEmployee(employee,1);

        Employee lastAfterSave = employeeDao.findAll()
                .stream()
                .max(Comparator.comparing(Employee::getId))
                .orElseThrow(NoSuchElementException::new);


        Assert.assertNotNull(lastAfterSave);
        Assert.assertEquals(lastAfterSave.getName(), employee.getName());
        Assert.assertEquals(lastAfterSave.getSalary(), employee.getSalary());

    }

    @Test
    public void updateDepartment() {
        Employee getLast = employeeDao.findAll().stream().reduce((first,second) -> second).orElse(null);
        Assert.assertNotNull(getLast);
        employeeDao.updateEmployee(getLast.getId(),employee,1);

        Employee lastAfterUpdate = employeeDao.findAll()
                .stream()
                .max(Comparator.comparing(Employee::getId))
                .orElseThrow(NoSuchElementException::new);

        Assert.assertNotNull(lastAfterUpdate);
        Assert.assertNotEquals(lastAfterUpdate.getName(), getLast.getName());
        Assert.assertEquals(lastAfterUpdate.getId(), getLast.getId());
    }

    @Test
    public void saveEmployeeForRestController() {
        employeeDao.saveEmployeeForRestController(employee);

        Employee lastAfterSave = jdbcTemplate.query("select * from employees", new EmployeeMapper()).stream().reduce((first, second) -> second).orElse(null);

        Assert.assertNotNull(lastAfterSave);
        Assert.assertEquals(lastAfterSave.getName(), employee.getName());
        Assert.assertEquals(lastAfterSave.getSalary(), employee.getSalary());
    }

    @Test
    public void updateEmployeeForRestController() {
        Employee getLast = employeeDao.findAll().stream().reduce((first,second) -> second).orElse(null);
        Assert.assertNotNull(getLast);

        employeeDao.updateEmployeeForRestController(employee, getLast.getId());
        Employee lastAfterUpdate = employeeDao.findAll().stream().reduce((first, second) -> second).orElse(null);

        Assert.assertNotNull(lastAfterUpdate);
        Assert.assertNotEquals(lastAfterUpdate.getName(), getLast.getName());
        Assert.assertEquals(lastAfterUpdate.getId(), getLast.getId());
    }

    @Test
    public void findEmployeeByName() {
        Employee getLast = employeeDao.findAll().stream().reduce((first,second) -> second).orElse(null);
        Assert.assertNotNull(getLast);
        Assert.assertNotNull(
                employeeDao.findEmployeeByName(getLast.getName())
        );
    }

    @Test
    public void deleteDepartment() {
        int sizeBeforeDelete = employeeDao.findAll().size();

        Employee getLast = employeeDao.findAll().stream().reduce((first,second) -> second).orElse(null);
        Assert.assertNotNull(getLast);

        employeeDao.deleteEmployee(getLast.getId());

        Assert.assertNotEquals(sizeBeforeDelete, employeeDao.findAll().size());
    }
}