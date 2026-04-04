package com.chetraseng.sunrise_task_flow_api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

public class JwtProperties {
    @Configuration
    @ConfigurationProperties(prefix = "jwt")
    @Getter
    @Setter
    public static class jwtProperties {
        private String secret;
        private long expiration;
    }
}
