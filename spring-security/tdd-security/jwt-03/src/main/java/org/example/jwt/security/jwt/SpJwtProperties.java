package org.example.jwt.security.jwt;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "sp.jwt")
public class SpJwtProperties {

    private String secret = "default-secret-value";
    private long tokenLifeTime = 600;
    private long tokenRefreshTime = 24*60*60; // 86400

}
