package org.example.EPAM_FARM.rest;

import org.example.EPAM_FARM.backend.model.Employee;
import org.example.EPAM_FARM.backend.service.EmployeeService;
import org.example.EPAM_FARM.rest.controller.RestEmployeeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.ArgumentMatchers.isA;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
public class RestEmployeeControllerMockTest {

    @InjectMocks
    private RestEmployeeController restEmployeeController;

    @Mock
    private EmployeeService employeeService;

    @Test
    public void getEmployees() {
        restEmployeeController.getEmployees();

        Mockito.verify(employeeService,Mockito.times(1))
                .findAll();
    }

    @Test
    public void getEmployee() {
        restEmployeeController.getEmployee(1);

        Mockito.verify(employeeService,Mockito.times(1))
                .findById(isA(Integer.class));
    }

    @Test
    public void create() {
        restEmployeeController.create(new Employee());

        Mockito.verify(employeeService,Mockito.times(1))
                .saveEmployeeForRestController(isA(Employee.class));
        Mockito.verify(employeeService,Mockito.times(1))
                .findAll();
    }

    @Test
    public void update() {
        restEmployeeController.update(1,new Employee());

        Mockito.verify(employeeService,Mockito.times(1))
                .updateEmployeeForRestController(isA(Employee.class),isA(Integer.class));
    }

    @Test
    public void delete() {
        restEmployeeController.delete(1);

        Mockito.verify(employeeService,Mockito.times(1))
                .deleteEmployee(isA(Integer.class));
    }
}
