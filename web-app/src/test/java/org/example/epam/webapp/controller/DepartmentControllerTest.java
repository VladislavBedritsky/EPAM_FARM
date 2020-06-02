package org.example.epam.webapp.controller;

import org.example.epam.backend.service.DepartmentService;
import org.example.epam.backend.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentControllerTest {

    @InjectMocks
    private DepartmentController departmentController;
    @Mock
    private UserService userService;
    @Mock
    private DepartmentService jdbcStorageService;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.departmentController).build();
    }


    @Test
    public void givenDepartmentsPageURI_whenMockMVC_thenReturnsDepartmentsViewName() throws Exception {

        Mockito.lenient().doReturn(true).when(userService).isUserHasAdminRole(ArgumentMatchers.isA(Authentication.class));
        Mockito.doReturn(new ArrayList<>()).when(jdbcStorageService).findAll();

        this.mockMvc.perform(MockMvcRequestBuilders.get("/departments/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("departments"));

        Mockito.verify(jdbcStorageService,Mockito.times(1)).findAll();
    }

}