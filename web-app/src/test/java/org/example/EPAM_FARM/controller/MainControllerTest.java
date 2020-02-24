package org.example.EPAM_FARM.controller;

import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class MainControllerTest {


    private DriverManagerDataSource driverManagerDataSource;
    private MainController mainController;

//    @Before
//    public void before() {
//        driverManagerDataSource = new DriverManagerDataSource();
//        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        driverManagerDataSource.setUrl("jdbc:mysql://192.168.99.1:3308/farm");
//        driverManagerDataSource.setUsername("root");
//        driverManagerDataSource.setPassword("root");
//
//        mainController = new MainController(driverManagerDataSource);
    }

//    @Test
//    public void getList () {
//        Integer q = mainController.findAll();
//        Assert.assertNotNull(q);
//    }
//}