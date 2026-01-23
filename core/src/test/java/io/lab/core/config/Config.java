package io.lab.core.config;

import io.lab.core.modules.admin.securityDetail.AdminDetailService;
import net.datafaker.Faker;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

@TestConfiguration
public class Config {

    @Bean
    public Faker faker(){
        return new Faker();
    }

    @Bean
    public UserDetailsService testUserDetailsService(AdminDetailService adminDetailService) {
        return adminDetailService;
    }
}
