package org.example.EPAM_FARM.backend.dao.impl;

import org.example.EPAM_FARM.backend.dao.UserDao;
import org.example.EPAM_FARM.backend.model.Role;
import org.example.EPAM_FARM.backend.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations={"classpath*:test.xml"})
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void findAll() {
        List<User> list = userDao.findAll();
        Assert.assertNotNull(list);
    }

    @Test
    public void findByUsername() {
        User getLast = userDao.findAll().stream().reduce((first,second) -> second).orElse(null);
        Assert.assertNotNull(getLast);

        User user = userDao.findByUsername(getLast.getUsername());
        Assert.assertNotNull(user);
    }

    @Test
    public void findUserRolesByUsername() {
        User getLast = userDao.findAll().stream().reduce((first,second) -> second).orElse(null);
        Assert.assertNotNull(getLast);

        List<Role> list = userDao.findUserRolesByUsername(getLast.getUsername());
        Assert.assertNotNull(list);
    }
}