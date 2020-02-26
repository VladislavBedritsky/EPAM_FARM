package org.example.EPAM_FARM.controller;

import org.example.EPAM_FARM.service.DepartmentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations={"classpath*:test-controller.xml"})
public class DepartmentControllerMockTest {

    @Mock
    private DepartmentService departmentService;

    @Test
    public void checkMethodGetDepartments() {
        Assert.assertNotNull(departmentService.findAll());
        Mockito.verify(departmentService, Mockito.times(1)).findAll();
    }


}
