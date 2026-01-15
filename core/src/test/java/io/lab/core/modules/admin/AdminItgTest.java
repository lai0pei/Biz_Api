package io.lab.core.modules.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lab.core.modules.admin.dto.request.AdminCreateReq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class AdminItgTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockMvcTester mockMvcTester;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    @DisplayName("Admin Integration Test")
    public void adminIntegrationTest() throws Exception {
        var reqC = AdminCreateReq.builder()
                .username("admin")
                .nickname("admin")
                .password("admin")
                .enabled(true)
                .roleId(1L)
                .superAdmin(true)
                .build();


        MvcTestResult addRes = mockMvcTester.perform(MockMvcRequestBuilders.post("/api/admin/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(reqC)));

        assertThat(addRes).hasStatusOk().bodyJson().hasPathSatisfying("$.data", resp -> resp.assertThat().isEqualTo(true));

//
//        request.put("id",1);
//        ResultActions updateRes = mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/update")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(this.objectMapper.writeValueAsString(request)));
//        updateRes.andExpect(jsonPath("$.data").value(true));
//
//
//        Map<String, Object> searchRequest = new HashMap<>();
//        searchRequest.put("Page", 0);
//        searchRequest.put("Size", 20);
//
//        ResultActions listRes = mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/list")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(this.objectMapper.writeValueAsString(searchRequest)));

    }

    @Test
    @WithMockUser
    @DisplayName("Admin Integration Test")
    public void adminIntegrationDelete() throws Exception {
//        Map<String, Object> request = new HashMap<>();
//        request.put("name", "admin");
//        request.put("username", "admin");
//        request.put("password", "admin");
//        request.put("roleId", 1);
//        request.put("enabled", 1);
//        request.put("isSuperAdmin", 1);
//
//        MvcTestResult addRes = mockMvcTester.perform(MockMvcRequestBuilders.post("/modules/admin/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(this.objectMapper.writeValueAsString(request)));
//        System.out.println(addRes);

//        mockMvcTester.perform(MockMvcRequestBuilders.post("/modules/admin/delete")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(this.objectMapper.writeValueAsString(Map.of("id", 1))));


        var del = mockMvcTester.perform(MockMvcRequestBuilders.post("/api/admin/delete"));
        assertThat(del).hasStatusOk();

    }
}
