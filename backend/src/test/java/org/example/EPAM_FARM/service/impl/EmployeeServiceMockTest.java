package org.example.EPAM_FARM.service.impl;

import org.example.EPAM_FARM.dao.EmployeeDao;
import org.example.EPAM_FARM.service.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
public class EmployeeServiceMockTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private EmployeeDao employeeDao;

    @Test
    public void findAll() {
        Assert.assertNotNull(employeeService.findAll());
        Assert.assertNotNull(employeeDao.findAll());

        Mockito.verify(employeeService, Mockito.times(1)).findAll();
        Mockito.verify(employeeDao, Mockito.times(1)).findAll();
    }


}
