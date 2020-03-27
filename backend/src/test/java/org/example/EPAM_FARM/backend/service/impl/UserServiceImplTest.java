package org.example.EPAM_FARM.backend.service.impl;

import org.example.EPAM_FARM.backend.dao.UserDao;
import org.example.EPAM_FARM.backend.model.Role;
import org.example.EPAM_FARM.backend.model.User;
import org.example.EPAM_FARM.backend.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

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

    @Mock
    private Model model;

    @Test
    public void loadUserByUsername() {

        when(userDao.findByUsername("username")).thenReturn(new User());
        when(userDao.findUserRolesByUsername("username")).thenReturn(new ArrayList<Role>());

        assertThat(userService.loadUserByUsername("username"), is(notNullValue()));

        Mockito.verify(userDao,Mockito.times(1))
                .findByUsername(isA(String.class));
        Mockito.verify(userDao,Mockito.times(1))
                .findUserRolesByUsername(isA(String.class));

    }

    @Test
    public void findUserRolesByUsername() {
        userService.findUserRolesByUsername("username");
        Mockito.verify(userDao,Mockito.times(1))
                .findUserRolesByUsername(isA(String.class));
    }


    @Test
    public void findAll() {
        userService.findAll();

        Mockito.verify(userDao,Mockito.times(1))
                .findAll();
    }

    @Test
    public void checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel() {
        when(authentication.getAuthorities()).thenReturn(new ArrayList<>());
        assertThat(userService.isUserHasAdminRole(authentication), is(notNullValue()));

    }

}