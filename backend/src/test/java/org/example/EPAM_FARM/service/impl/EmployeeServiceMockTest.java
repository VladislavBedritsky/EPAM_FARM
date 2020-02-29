package org.example.EPAM_FARM.service.impl;

import org.example.EPAM_FARM.dao.EmployeeDao;
import org.example.EPAM_FARM.model.Employee;
import org.example.EPAM_FARM.service.ConverterService;
import org.example.EPAM_FARM.service.DepartmentService;
import org.example.EPAM_FARM.service.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.isA;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
public class EmployeeServiceMockTest {

    @InjectMocks
    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Mock
    private EmployeeDao employeeDao;

    @Mock
    private DepartmentService departmentService;

    @Mock
    private ConverterService converterService;

    private Employee employee;

    @Before
    public void setUp() {
        employee = new Employee();
        employee.setName("Employee");
        employee.setSalary(123.01f);
        employee.setBirthday(LocalDate.of(2012, Month.APRIL,12));
    }

    @Test
    public void findAll() {
        Mockito.when(employeeDao.findAll()).thenReturn(
                Stream.of(new Employee(),new Employee()).collect(Collectors.toList())
        );

        Assert.assertEquals(2,employeeService.findAll().size());
    }

    @Test
    public void findById() {

        Mockito.when(employeeDao.findById(1)).thenReturn(
                new Employee()
        );

        Assert.assertNotNull(employeeService.findById(1));
    }

    @Test
    public void saveEmployee() {
        employeeService.saveEmployee(employee,"Department");

        Mockito.verify(employeeDao,Mockito.times(1)).saveEmployee(isA(Employee.class), isA(Integer.class));
        Mockito.verify(departmentService,Mockito.times(1)).findDepartmentIdByDepartmentName(isA(String.class));
    }

    @Test
    public void updateEmployee () {
        employeeService.updateEmployee(1,employee,"Department");

        Mockito.verify(employeeDao,Mockito.times(1))
                .updateEmployee(
                        isA(Integer.class),
                        isA(Employee.class),
                        isA(Integer.class));
    }

    @Test
    public void returnNewEmployeeWithSetParameters() {
        employeeService.returnNewEmployeeWithSetParameters("name","birthday","salary");

        Mockito.verify(converterService,Mockito.times(1))
                .convertFromStringToFloat(
                        isA(String.class));
        Mockito.verify(converterService,Mockito.times(1))
                .convertStringToLocalDate(
                        isA(String.class));
    }

    @Test
    public void deleteEmployee() {
        employeeService.deleteEmployee(1);

        Mockito.verify(employeeDao,Mockito.times(1))
                .deleteEmployee(
                        isA(Integer.class));
    }

    @Test
    public void saveEmployeeForRestController() {
        employeeService.saveEmployeeForRestController(employee);

        Mockito.verify(employeeDao,Mockito.times(1))
                .saveEmployeeForRestController(
                        isA(Employee.class));
    }

    @Test
    public void updateEmployeeForRestController() {
        employeeService.updateEmployeeForRestController(employee,1);

        Mockito.verify(employeeDao,Mockito.times(1))
                .updateEmployeeForRestController(
                        isA(Employee.class),
                        isA(Integer.class));
    }

    @Test
    public void findEmployeeByName() {
        employeeService.findEmployeeByName("emloyee_name");

        Mockito.verify(employeeDao,Mockito.times(1))
                .findEmployeeByName(
                        isA(String.class));
    }
}
