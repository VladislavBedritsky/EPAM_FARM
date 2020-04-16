package org.example.epam.webapp.controller;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations={"classpath*:test-org.example.epam.rest.controller.xml"})
public class MainControllerTest {

    @InjectMocks
    private MainController mainController;
    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.mainController).build();
    }

    @Test
    public void givenMainPageURI_whenMockMVC_thenReturnsIndexViewName() throws Exception {

        Mockito.lenient().doReturn(true).when(userService).isUserHasAdminRole(ArgumentMatchers.isA(Authentication.class));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    public void givenLoginPageURI_whenMockMVC_thenReturnsLoginViewName() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/login/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("login"));
    }

    @Test
    public void givenAccessDeniedPageURI_whenMockMVC_thenReturnsAccessDeniedViewName() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/accessdenied/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("denied"));
    }
}