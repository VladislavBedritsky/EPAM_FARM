package org.example.epam.webapp.controller;

import org.example.epam.backend.service.DepartmentService;
import org.example.epam.backend.service.EmployeeService;
import org.example.epam.backend.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.isA;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerMockTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private DepartmentService departmentService;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Test
    public  void getEmployees() {
        employeeController.getEmployees(model);

        Mockito.verify(employeeService,Mockito.times(1))
                .findAll();
        Mockito.verify(departmentService,Mockito.times(1))
                .findAll();
        Mockito.verify(userService,Mockito.times(1))
                .checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseThanAddToModel(isA(Model.class));
    }

    @Test
    public void getEmployeeView() {
        employeeController.getEmployeeView(model,1);

        Mockito.verify(employeeService,Mockito.times(1))
                .findById(isA(Integer.class));
        Mockito.verify(userService,Mockito.times(1))
                .checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseThanAddToModel(isA(Model.class));
    }

    @Test
    public void saveEmployee() {
        employeeController.saveEmployee("name","birthday","salary","department_name",model);

        Mockito.verify(employeeService,Mockito.times(1))
                .findAll();
        Mockito.verify(employeeService,Mockito.times(1))
                .isEmployeeNameAlreadyExists(isA(String.class));
        Mockito.verify(employeeService,Mockito.times(1))
                .isProperFloatValue(isA(String.class));

    }

    @Test
    public void deleteEmployee() {
        employeeController.deleteEmployee(1);

        Mockito.verify(employeeService,Mockito.times(1))
                .deleteEmployee(isA(Integer.class));
    }

    @Test
    public void updateEmpoyee() {
        employeeController.updateEmployee(1,"name","birthday","salary","department_name",model);

        Mockito.verify(employeeService,Mockito.times(1))
                .findAll();
    }
}
