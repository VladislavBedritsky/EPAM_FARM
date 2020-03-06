package org.example.EPAM_FARM.controller;

import org.example.EPAM_FARM.model.User;
import org.example.EPAM_FARM.service.DepartmentService;
import org.example.EPAM_FARM.service.EmployeeService;
import org.example.EPAM_FARM.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.isA;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
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
        employeeController.getEmployees(new User(),model);

        Mockito.verify(employeeService,Mockito.times(1))
                .findAll();
        Mockito.verify(departmentService,Mockito.times(1))
                .findAll();
        Mockito.verify(userService,Mockito.times(1))
                .checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel(isA(Model.class),isA(User.class));
    }

    @Test
    public void getEmployeeView() {
        employeeController.getEmployeeView(new User(),model,1);

        Mockito.verify(employeeService,Mockito.times(1))
                .findById(isA(Integer.class));
        Mockito.verify(userService,Mockito.times(1))
                .checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel(isA(Model.class),isA(User.class));
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
