package io.lab.core.modules.role;


import io.lab.core.modules.role.dto.request.PermGrant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import tools.jackson.databind.ObjectMapper;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class RoleUnitTest {

    @Autowired
    private MockMvcTester mockMvcTester;

    @Autowired
    private MockMvc mockMvc;

   @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "adminH")
    public void addTest()  throws Exception{
        var n = new PermGrant(1L,Set.of(1L,2L,3L,4L));


        var v = mockMvc.perform(post("/modules/role/permGrantIdss")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(n)))
                .andDo(print());

    }
}
