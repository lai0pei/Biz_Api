package io.lab.core.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@TestConfiguration(proxyBeanMethods = false)
public class Database{

    @Autowired
    private Environment env;

    @Bean
    @Primary
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create()
                .url(env.getProperty("app.test.datasource.url"))
                .username(env.getProperty("app.test.datasource.username"))
                .password(env.getProperty("app.test.datasource.password"))
                .driverClassName(env.getProperty("app.test.datasource.driver-class-name"))
                .build();
    }


}
