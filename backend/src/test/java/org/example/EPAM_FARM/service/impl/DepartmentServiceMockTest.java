package org.example.EPAM_FARM.service.impl;

import org.example.EPAM_FARM.dao.DepartmentDao;
import org.example.EPAM_FARM.service.DepartmentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
public class DepartmentServiceMockTest {

    @Mock
    private DepartmentService departmentService;

    @Mock
    private DepartmentDao departmentDao;

    @Test
    public void findAll() {
        Assert.assertNotNull(departmentService.findAll());
        Assert.assertNotNull(departmentDao.findAll());

        Mockito.verify(departmentService, Mockito.times(1)).findAll();
        Mockito.verify(departmentDao, Mockito.times(1)).findAll();
    }

}
