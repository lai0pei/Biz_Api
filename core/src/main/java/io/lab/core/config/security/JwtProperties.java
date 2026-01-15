package io.lab.core.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix ="app.jwt")
@Component
@Data
public class JwtProperties {
    private String secret;
    private String prefix;
    private long accessExpirationMs;
    private long refreshExpirationMs;
}

