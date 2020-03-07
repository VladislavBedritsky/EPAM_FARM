//package org.example.EPAM_FARM.controller;
//
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath*:test-controller.xml"})
//public class DepartmentControllerTest {
//
//    @Autowired
//    private DepartmentController departmentController;
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setup() throws Exception {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//    }
//
//    @Test
//    public void givenWac_whenServletContext_thenItProvidesDepartmentController() {
//        ServletContext servletContext = wac.getServletContext();
//
//        Assert.assertNotNull(servletContext);
//        Assert.assertTrue(servletContext instanceof MockServletContext);
//        Assert.assertNotNull(wac.getBean("departmentController"));
//
//    }
//
//    @Test
//    public void givenDepartmentsPageURI_whenMockMVC_thenReturnsDepartmentsViewName() throws Exception {
//        this.mockMvc.perform(get("/departments/")).andExpect(view().name("departments"));
//    }
//
//
//}