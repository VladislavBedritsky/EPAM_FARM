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
public class JdbcStorageServiceMockTest {

    @Mock
    private DepartmentService jdbcStorageService;

    @Test
    public void findAll() {

        Mockito.when(jdbcStorageService.findAll()).thenReturn(
                Stream.of(new Department(1,"ss")).collect(Collectors.toList())
        );

        Assert.assertEquals(1,jdbcStorageService.findAll().size());
    }

    @Test
    public void findById() {

        Mockito.when(jdbcStorageService.findById(1)).thenReturn(
                new Department(1,"ss")
        );

        Assert.assertNotNull(jdbcStorageService.findById(1));
    }

    @Test
    public void saveDepartment() {
        Department department = new Department(1,"qq");
        doNothing().when(jdbcStorageService).saveDepartment(isA(Department.class));
        jdbcStorageService.saveDepartment(department);

        Mockito.verify(jdbcStorageService,Mockito.times(1)).saveDepartment(department);
    }

    @Test
    public void updateDepartment () {
        doNothing().when(jdbcStorageService).updateDepartment(isA(Integer.class),isA(String.class));
        jdbcStorageService.updateDepartment(1,"ss");

        Mockito.verify(jdbcStorageService,Mockito.times(1)).updateDepartment(1,"ss");
    }

    @Test
    public void deleteDepartment() {
        doNothing().when(jdbcStorageService).deleteDepartment(isA(Integer.class));
        jdbcStorageService.deleteDepartment(1);

        Mockito.verify(jdbcStorageService, Mockito.times(1)).deleteDepartment(1);
    }

}
