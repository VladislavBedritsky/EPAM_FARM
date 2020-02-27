package org.example.EPAM_FARM.service.impl;

import org.example.EPAM_FARM.model.Department;
import org.example.EPAM_FARM.service.DepartmentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
public class DepartmentServiceMockTest {

    @Mock
    private DepartmentService departmentService;

    @Test
    public void findAll() {

        Mockito.when(departmentService.findAll()).thenReturn(
                Stream.of(new Department(1,"ss")).collect(Collectors.toList())
        );

        Assert.assertEquals(1,departmentService.findAll().size());
    }

    @Test
    public void findById() {

        Mockito.when(departmentService.findById(1)).thenReturn(
                new Department(1,"ss")
        );

        Assert.assertNotNull(departmentService.findById(1));
    }

    @Test
    public void saveDepartment() {
        Department department = new Department(1,"qq");
        doNothing().when(departmentService).saveDepartment(isA(Department.class));
        departmentService.saveDepartment(department);

        Mockito.verify(departmentService,Mockito.times(1)).saveDepartment(department);
    }

    @Test
    public void updateDepartment () {
        doNothing().when(departmentService).updateDepartment(isA(Integer.class),isA(String.class));
        departmentService.updateDepartment(1,"ss");

        Mockito.verify(departmentService,Mockito.times(1)).updateDepartment(1,"ss");
    }

    @Test
    public void deleteDepartment() {
        doNothing().when(departmentService).deleteDepartment(isA(Integer.class));
        departmentService.deleteDepartment(1);

        Mockito.verify(departmentService, Mockito.times(1)).deleteDepartment(1);
    }

}
