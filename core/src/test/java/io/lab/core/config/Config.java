package io.lab.core.config;

import net.datafaker.Faker;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class Config {

    @Bean
    public Faker faker(){
        return new Faker();
    }
}
