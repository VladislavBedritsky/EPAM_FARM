package org.example.EPAM_FARM.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class UserTest {


    private static final Integer USER_ID = 1;
    private static final String USER_FIRSTNAME = "MIKE";
    private static final String USER_LASTNAME = "BLACK";
    private static final String USER_USENAME = "QQ";
    private static final String USER_PASSWORD = "QW";
    private static final String USER_EMAIL = "mike@mail.ru";
    private static final LocalDate USER_BIRTHDAY = LocalDate.of(2014, Month.APRIL,1);
    private static final Boolean USER_ACTIVE=true;
    private List<Role> roleList;
    private User user;

    @Before
    public void setUp() {
        roleList = new ArrayList<>();
        roleList.add(Role.ADMIN);
        user = new User();
    }

    @Test
    public void getId () {
        user.setId(1);

        Assert.assertNotNull(user);
        Assert.assertEquals(user.getId(),USER_ID);
    }

    @Test
    public void getFirstName() {
        user.setFirstName("MIKE");

        Assert.assertNotNull(user);
        Assert.assertEquals(user.getFirstName(),USER_FIRSTNAME);
    }

    @Test
    public void getLastName() {
        user.setLastName("BLACK");

        Assert.assertNotNull(user);
        Assert.assertEquals(user.getLastName(),USER_LASTNAME);
    }

    @Test
    public void getUserName() {
        user.setUsername("QQ");

        Assert.assertNotNull(user);
        Assert.assertEquals(user.getUsername(),USER_USENAME);
    }

    @Test
    public void getPassword() {
        user.setPassword("QW");

        Assert.assertNotNull(user);
        Assert.assertEquals(user.getPassword(),USER_PASSWORD);
    }

    @Test
    public void getEmail() {
        user.setEmail("mike@mail.ru");

        Assert.assertNotNull(user);
        Assert.assertEquals(user.getEmail(), USER_EMAIL);
    }

    @Test
    public void getBirthday() {
        user.setBirthday(LocalDate.of(2014, Month.APRIL,1));

        Assert.assertNotNull(user);
        Assert.assertEquals(user.getBirthday(), USER_BIRTHDAY);
    }

    @Test
    public void getActive() {
        user.setActive(true);

        Assert.assertNotNull(user);
        Assert.assertTrue(user.isActive());
        Assert.assertEquals(user.isActive(),USER_ACTIVE);
    }

    @Test
    public void getUserRoles() {
        user.setRoles(roleList);

        Assert.assertNotNull(user);
        Assert.assertEquals(user.getRoles(),roleList);
        Assert.assertEquals(user.getRoles().size(),1);
    }
}