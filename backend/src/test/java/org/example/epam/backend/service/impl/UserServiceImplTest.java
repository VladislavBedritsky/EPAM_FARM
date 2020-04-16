package org.example.epam.backend.service.impl;

import org.example.epam.backend.dao.UserDao;
import org.example.epam.backend.model.Role;
import org.example.epam.backend.model.User;
import org.example.epam.backend.service.UserService;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
public class UserServiceImplTest {

    @InjectMocks
    @Spy
    private UserService userService = new UserServiceImpl();

    @Mock
    private UserDao userDao;

    @Mock
    private Authentication authentication;

    @Test
    public void loadUserByUsername() {

        Mockito.when(userDao.findByUsername("username")).thenReturn(new User());
        Mockito.when(userDao.findUserRolesByUsername("username")).thenReturn(new ArrayList<Role>());

        Assert.assertThat(userService.loadUserByUsername("username"), Is.is(IsNull.notNullValue()));

        Mockito.verify(userDao,Mockito.times(1))
                .findByUsername(Mockito.isA(String.class));
        Mockito.verify(userDao,Mockito.times(1))
                .findUserRolesByUsername(Mockito.isA(String.class));

    }

    @Test
    public void findUserRolesByUsername() {
        userService.findUserRolesByUsername("username");
        Mockito.verify(userDao,Mockito.times(1))
                .findUserRolesByUsername(Mockito.isA(String.class));
    }


    @Test
    public void findAll() {
        userService.findAll();

        Mockito.verify(userDao,Mockito.times(1))
                .findAll();
    }

    @Test
    public void checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel() {
        Mockito.when(authentication.getAuthorities()).thenReturn(new ArrayList<>());
        Assert.assertThat(userService.isUserHasAdminRole(authentication), Is.is(IsNull.notNullValue()));

    }

}