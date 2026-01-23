package io.lab.core.modules.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lab.core.modules.admin.dto.request.AdminCreateReq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails(value = "admin") // db need to be populated first
public class AdminItgTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvcTester mockMvcTester;

    private MockMvc mockMvc;

    @BeforeEach
    public void beforeEach() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

        this.mockMvcTester = MockMvcTester.create(this.mockMvc);
    }


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Admin Integration Test")
//    @WithUserDetails(value = "admin") // db need to be populated first
    public void adminIntegrationTest() throws Exception {
        var reqC = AdminCreateReq.builder()
                .username("new_admin")
                .nickname("admin")
                .password("admin")
                .enabled(true)
                .roleId(1L)
                .superAdmin(true)
                .build();

        mockMvc.perform(post("/modules/admin/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reqC)))
                .andExpect(
                        status().isOk()
                );
    }

    @Test
    @DisplayName("Admin Integration Test")
    public void adminIntegrationTest1() throws Exception {


        mockMvc.perform(post("/modules/admin/list")
                        .contentType(MediaType.APPLICATION_JSON)
                      )
                .andExpect(
                        status().isOk()
                );


    }

}
