package io.lab.core.modules.admin;


import io.lab.core.modules.admin.dto.request.AdminSearchReq;
import io.lab.core.modules.admin.dto.response.AdminSearchResp;
import io.lab.core.modules.role.RoleMdl;
import io.lab.core.config.Config;
import io.lab.core.config.Database;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@Disabled
@Rollback(false)
@Import({AdminSrv.class, Database.class, Config.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdminRepoTest {

    @Autowired
    private AdminRepo adminRepo;

    @MockitoBean
    private AdminMpp adminMpp;

    @Autowired
    private AdminSrv adminSrv;

    @Autowired
    private Faker faker;

    @MockitoBean
    private PasswordEncoder passwordEncoder;


    private AdminMdl makeAdmin(){
        AdminMdl adminMdl = new AdminMdl();
        adminMdl.setNickname(this.faker.name().fullName());
        adminMdl.setUsername(this.faker.name().fullName());
        adminMdl.setPassword(this.faker.credentials().password());
        adminMdl.setSuperAdmin(false);
        adminMdl.setEnabled(true);
        var role = RoleMdl.builder().id(1L).roleName("rom").build();
        adminMdl.setRole(role);
        adminMdl.setLastLoginTime(LocalDateTime.now());
        return adminMdl;
    }

    @Test
    void printOutput(){
        System.out.println(makeAdmin().toString());
    }

    @Test
    void createAdmin() throws Exception {
        AdminMdl res = adminRepo.save(makeAdmin());
        assertThat(res).isNotNull();
    }

    @Test
    void updateAdmin() throws Exception {
//        adminRepo.save(makeAdmin());
        Optional<AdminMdl> admin = adminRepo.findById(UUID.randomUUID());
        if (admin.isEmpty()) {
            throw new Exception("update failed");
        }
        AdminMdl adminMdl = admin.get();
        adminMdl.setId(UUID.randomUUID());
        adminMdl.setUsername("update_admin");
        adminMdl.setNickname("update_nick");
        adminMdl.setPassword("update_HELLO");

//        AdminMdl res = adminRepo.save(adminMdl);
//        assertThat(res)
//                .extracting(AdminMdl::getUsername, AdminMdl::getName, AdminMdl::getPassword)
//                .containsExactly("update_admin", "update_nick", "update_HELLO");
    }

    @Test
    void deleteAdmin() throws Exception {
        adminRepo.save(makeAdmin());
    }

    @Test
    void listAdmin() throws Exception {
        var adminSearchReq = AdminSearchReq.builder()
                .size(10)
                .page(0)
                .build();

        Page<AdminSearchResp> res = adminSrv.listAllAdmin(adminSearchReq);
        System.out.println(res.getContent());
    }

    @Test
    void getRole() throws Exception {
        var  a  = this.adminRepo.findByUsername("admin1").orElseThrow(()->new Exception("not found"));
        assertThat(a.getRole().getId()).isEqualTo(1L);
    }

}
