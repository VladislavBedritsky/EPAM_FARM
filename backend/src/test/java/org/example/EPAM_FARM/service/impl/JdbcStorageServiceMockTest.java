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
public class JdbcStorageServiceMockTest {

    @Mock
    private DepartmentDao jdbcStorageDao;

    @Mock
    private DepartmentService jdbcStorageService;

    @Test
    public void findAll() {
        Assert.assertNotNull(jdbcStorageService.findAll());
        Assert.assertNotNull(jdbcStorageDao.findAll());

        Mockito.verify(jdbcStorageService, Mockito.times(1)).findAll();
        Mockito.verify(jdbcStorageDao, Mockito.times(1)).findAll();
    }
}
