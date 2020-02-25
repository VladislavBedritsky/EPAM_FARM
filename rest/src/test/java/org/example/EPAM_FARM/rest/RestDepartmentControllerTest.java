package org.example.EPAM_FARM.rest;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:test-rest-controller.xml"})
public class RestDepartmentControllerTest {

    @Autowired
    private RestDepartmentController restDepartmentController;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesRestDepartmentController() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("restDepartmentController"));
    }

    @Test
    public void givenDepartmentURI_whenMockMVC_thenVerifyResponse() throws Exception {
        this.mockMvc.perform(get("/departments")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

}