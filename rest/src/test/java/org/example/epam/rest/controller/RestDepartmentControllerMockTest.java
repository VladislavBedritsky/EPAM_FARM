package org.example.epam.rest.controller;

import org.example.epam.backend.model.Department;
import org.example.epam.backend.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;

@RunWith(MockitoJUnitRunner.class)
public class RestDepartmentControllerMockTest {

    @InjectMocks
    private RestDepartmentController restDepartmentController;

    @Mock
    private DepartmentService jdbcStorageService;


    @Test
    public void getDepartments() {
        restDepartmentController.getDepartments();

        Mockito.verify(jdbcStorageService,Mockito.times(1))
                .findAll();
    }

    @Test
    public void getDepartment() {
        restDepartmentController.getDepartment(1);

        Mockito.verify(jdbcStorageService,Mockito.times(1))
                .findById(isA(Integer.class));
    }

    @Test
    public void create() {
        restDepartmentController.create(new Department());

        Mockito.verify(jdbcStorageService,Mockito.times(1))
                .saveDepartment(isA(Department.class));

    }

    @Test
    public void update() {
        restDepartmentController.update(1,new Department());

        Mockito.verify(jdbcStorageService,Mockito.times(1))
                .updateDepartment(isA(Integer.class),eq(null));
    }

    @Test
    public void delete() {
        restDepartmentController.delete(1);

        Mockito.verify(jdbcStorageService,Mockito.times(1))
                .deleteDepartment(isA(Integer.class));
    }


}
