package org.example.epamFarm.webApp.controller;

import org.example.epamFarm.backend.service.DepartmentService;
import org.example.epamFarm.backend.service.EmployeeService;
import org.example.epamFarm.backend.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations={"classpath*:test-controller.xml"})
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private DepartmentService departmentService;
    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.employeeController).build();
    }


    @Test
    public void givenEmployeesPageURI_whenMockMVC_thenReturnsEmployeesViewName() throws Exception {

        Mockito.lenient().doReturn(true).when(userService).isUserHasAdminRole(ArgumentMatchers.isA(Authentication.class));
        Mockito.doReturn(new ArrayList<>()).when(employeeService).findAll();
        Mockito.doReturn(new ArrayList<>()).when(departmentService).findAll();

        this.mockMvc.perform(MockMvcRequestBuilders.get("/employees/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("employees"));

        Mockito.verify(employeeService,Mockito.times(1)).findAll();
        Mockito.verify(departmentService,Mockito.times(1)).findAll();
    }

}