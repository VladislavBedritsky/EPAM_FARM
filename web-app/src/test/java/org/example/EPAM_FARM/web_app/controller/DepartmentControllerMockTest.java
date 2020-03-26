package org.example.EPAM_FARM.web_app.controller;

import org.example.EPAM_FARM.backend.service.DepartmentService;
import org.example.EPAM_FARM.backend.service.UserService;
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
public class DepartmentControllerMockTest {

    @InjectMocks
    private DepartmentController departmentController;

    @Mock
    private DepartmentService departmentService;

    @Mock
    private DepartmentService jdbcStorageService;

    @Mock
    private UserService userService;

    @Mock
    private Model model ;

    @Test
    public void getDepartments() {
        departmentController.getDepartments(model);

        Mockito.verify(jdbcStorageService,Mockito.times(1))
                .findAll();
        Mockito.verify(userService,Mockito.times(1))
                .checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel(isA(Model.class));

    }

    @Test
    public void saveDepartment() {
        departmentController.saveDepartment("name", model);

        Mockito.verify(jdbcStorageService,Mockito.times(1))
                .findAll();
        Mockito.verify(departmentService,Mockito.times(1))
                .isDepartmentNameAlreadyExists(isA(String.class));
        Mockito.verify(departmentService,Mockito.times(1))
                .returnNewDepartmentWithName(isA(String.class));

        Mockito.verify(jdbcStorageService,Mockito.times(1))
                .saveDepartment(null);

    }

    @Test
    public void getDepartmentView() {
        departmentController.getDepartmentView(model,1);

        Mockito.verify(jdbcStorageService,Mockito.times(1))
                .findById(isA(Integer.class));
        Mockito.verify(departmentService,Mockito.times(1))
                .findEmployeesByDepartmentId(isA(Integer.class));
        Mockito.verify(departmentService,Mockito.times(1))
                .getAverageSalaryInDepartment(isA(Integer.class));
        Mockito.verify(userService,Mockito.times(1))
                .checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel(isA(Model.class));
    }

    @Test
    public void deleteDepartment() {
        departmentController.deleteDepartment(1);

        Mockito.verify(jdbcStorageService,Mockito.times(1))
                .deleteDepartment(isA(Integer.class));
    }

    @Test
    public void updateDepartment() {
        departmentController.updateDepartment(1,"name");

        Mockito.verify(jdbcStorageService,Mockito.times(1))
                .updateDepartment(isA(Integer.class), isA(String.class));
    }

}
