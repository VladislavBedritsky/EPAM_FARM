package org.example.EPAM_FARM.controller;


import org.example.EPAM_FARM.service.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations={"classpath*:test-controller.xml"})
public class EmployeeControllerMockTest {

    @Mock
    private EmployeeService employeeService;

    @Test
    public void checkMethodGetEmployees() {
        Assert.assertNotNull(employeeService.findAll());
        Mockito.verify(employeeService, Mockito.times(1)).findAll();
    }

}
