package org.example.EPAM_FARM.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:test-controller.xml"})
public class MainControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesMainController() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("mainController"));

    }

//    @Test
//    public void givenMainPageURI_whenMockMVC_whenUserIsAuthenticated_thenReturnsMainViewName() throws Exception {
//        this.mockMvc.perform(get("/")).andExpect(view().name("index"));
//    }
//
//    @Test
//    public void givenLoginPageURI_whenMockMVC_thenReturnsLoginViewName() throws Exception {
//        this.mockMvc.perform(get("/login")).andExpect(view().name("login"));
//    }

    @Test
    public void givenAccessDeniedPageURI_whenMockMVC_thenReturnsAccessDeniedViewName() throws Exception {
        this.mockMvc.perform(get("/accessdenied")).andExpect(view().name("denied"));
    }

}