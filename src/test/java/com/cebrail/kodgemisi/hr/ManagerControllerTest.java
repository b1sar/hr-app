package com.cebrail.kodgemisi.hr;

import com.cebrail.kodgemisi.hr.Controller.ManagerController;
import com.cebrail.kodgemisi.hr.Service.JobApplicationService;
import com.cebrail.kodgemisi.hr.Service.JobListingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@WebMvcTest(ManagerController.class)
public class ManagerControllerTest {

    @MockBean
    public JobListingService jobListingService;

    @MockBean
    public JobApplicationService jobApplicationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void managerHomeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/manager/home"))
                //expect status to be not authenticated
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/manager/login"));
    }

    @Test
    public void getHomeAsManager() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/manager/home").with(user("manager").password("manager").roles("MANAGER")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void listJobsAsUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/manager/listjobs").with(user("user").password("user").roles("USER")))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void loginAsManager() throws Exception {
        mockMvc.perform(formLogin("/manager/login").user("manager").password("manager"))
                .andExpect(SecurityMockMvcResultMatchers.authenticated().withUsername("manager").withRoles("MANAGER"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/manager/home"));
    }

    @Test
    public void unsuccessfulLogin() throws Exception {
       mockMvc.perform(formLogin("/manager/login").user("notexists").password("notexists"))
               .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }

    @Test
    public void logoutFromManager() throws Exception {
        mockMvc.perform(logout("/manager/logout"))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

}
