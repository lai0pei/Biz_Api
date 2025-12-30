package io.lab.core.api.admin;


import io.lab.core.api.admin.request.SearchDto;
import io.lab.core.api.admin.response.SearchResult;
import io.lab.core.config.Config;
import io.lab.core.config.Database;
import jakarta.persistence.EntityManager;
import net.datafaker.Faker;
import org.junit.jupiter.api.Disabled;
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

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@Disabled
@Rollback(false)
@Import({AdminService.class, Database.class, Config.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdminRepoTest {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private Faker faker;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityManager entityManager;

    private AdminModel makeAdmin(){
        AdminModel adminModel = new AdminModel();
        adminModel.setName(this.faker.name().fullName());
        adminModel.setUsername(this.faker.name().fullName());
        adminModel.setPassword(this.faker.credentials().password());
        adminModel.setSuperAdmin(false);
        adminModel.setEnabled(true);
        adminModel.setRoleId(this.faker.number().numberBetween(1,3));
        adminModel.setLastLoginTime(LocalDateTime.now());
        return adminModel;
    }

    @Test
    void printOutput(){
        System.out.println(makeAdmin().toString());
    }

    @Test
    void createAdmin() throws Exception {
        AdminModel res = adminRepository.save(makeAdmin());
        assertThat(res).isNotNull();
    }

    @Test
    void updateAdmin() throws Exception {
//        adminRepository.save(makeAdmin());
        Optional<AdminModel> admin = adminRepository.findById(1L);
        if (admin.isEmpty()) {
            throw new Exception("update failed");
        }
        AdminModel adminModel = admin.get();
        adminModel.setId(1);
        adminModel.setUsername("update_admin");
        adminModel.setName("update_nick");
        adminModel.setPassword("update_HELLO");

//        AdminModel res = adminRepository.save(adminModel);
//        assertThat(res)
//                .extracting(AdminModel::getUsername, AdminModel::getName, AdminModel::getPassword)
//                .containsExactly("update_admin", "update_nick", "update_HELLO");
    }

    @Test
    void deleteAdmin() throws Exception {
        adminRepository.save(makeAdmin());
    }

    @Test
    void listAdmin() throws Exception {
        SearchDto searchDto = new SearchDto();
        searchDto.setPage(0);
        searchDto.setSize(10);
        searchDto.setUsername("new_ad");
        Page<SearchResult> res = adminService.listAllAdmin(searchDto);
        System.out.println(res.getContent());
        System.out.println("hello");
    }
}
