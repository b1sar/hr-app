package com.cebrail.kodgemisi.hr;


import com.cebrail.kodgemisi.hr.Controller.UserController;
import com.cebrail.kodgemisi.hr.Service.JobApplicationService;
import com.cebrail.kodgemisi.hr.Service.JobListingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static  org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public JobListingService jobListingService;

    @MockBean
    public JobApplicationService jobApplicationService;


    @Test
    @WithMockUser
    public void testHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/home"))
                .andExpect(authenticated().withRoles("USER"));
    }

    @Test
    @WithMockUser(username = "manager", password = "manager", roles = "MANAGER")
    public void getHomeAsManager() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/home"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(formLogin("/user/login").user("user").password("user"))
                .andExpect(SecurityMockMvcResultMatchers.authenticated().withRoles("USER"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/home"));
    }

    @Test
    @WithMockUser
    public void testLogout() throws Exception {
        mockMvc.perform(SecurityMockMvcRequestBuilders.logout("/user/logout"))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }
}
