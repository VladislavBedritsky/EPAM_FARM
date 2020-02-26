package org.example.EPAM_FARM.rest;


import org.example.EPAM_FARM.service.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations={"classpath*:test-rest-controller.xml"})
public class RestEmployeeControllerMockTest {

    @Mock
    private EmployeeService employeeService;

    @Test
    public void checkMethodGetEmployees() {
        Assert.assertNotNull(employeeService.findAll());
        Mockito.verify(employeeService, Mockito.times(1)).findAll();
    }

}
