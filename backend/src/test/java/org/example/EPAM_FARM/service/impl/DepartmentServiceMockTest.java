package org.example.EPAM_FARM.service.impl;

import org.example.EPAM_FARM.dao.DepartmentDao;
import org.example.EPAM_FARM.dao.EmployeeDao;
import org.example.EPAM_FARM.model.Department;
import org.example.EPAM_FARM.service.DepartmentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.isA;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
public class DepartmentServiceMockTest {

    @InjectMocks
    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Mock
    private DepartmentDao departmentDao;

    @Mock
    private EmployeeDao employeeDao;


    @Test
    public void findAll() {

        Mockito.when(departmentDao.findAll()).thenReturn(
                Stream.of(new Department(1,"ss")).collect(Collectors.toList())
        );

        Assert.assertEquals(1,departmentService.findAll().size());
    }

    @Test
    public void findById() {

        Mockito.when(departmentDao.findById(1)).thenReturn(
                new Department(1,"ss")
        );

        Assert.assertNotNull(departmentService.findById(1));
    }

    @Test
    public void saveDepartment() {
        Department department = new Department();
        department.setName("Department");
        departmentService.saveDepartment(department);

        Mockito.verify(departmentDao,Mockito.times(1)).saveDepartment(isA(Department.class));
    }

    @Test
    public void updateDepartment () {
        departmentService.updateDepartment(1,"ss");

        Mockito.verify(departmentDao,Mockito.times(1)).updateDepartment(isA(Integer.class),isA(String.class));
    }

    @Test
    public void deleteDepartment() {
        departmentService.deleteDepartment(1);

        Mockito.verify(departmentDao, Mockito.times(1)).deleteDepartment(isA(Integer.class));
    }

    @Test
    public void findEmployeeByDepartmentId() {
        departmentService.findEmployeesByDepartmentId(1);

        Mockito.verify(employeeDao,Mockito.times(1)).findEmployeesByDepartmentId(isA(Integer.class));
    }

    @Test
    public void getAverageSalaryInDepartment() {
        departmentService.getAverageSalaryInDepartment(1);

        Mockito.verify(departmentDao, Mockito.times(1)).getAverageSalaryInDepartment(isA(Integer.class));
    }
}
