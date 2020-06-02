package org.example.epam.rest.controller;

import org.example.epam.backend.model.Department;
import org.example.epam.backend.model.Employee;
import org.example.epam.backend.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.isA;

@RunWith(MockitoJUnitRunner.class)
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
        Department department = new Department();
        department.setName("department");
        Employee employee = new Employee();
        employee.setDepartment(department);

        restEmployeeController.saveEmployee(employee);
        Mockito.verify(employeeService,Mockito.times(1))
                .saveEmployee(isA(Employee.class),isA(String.class));
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
