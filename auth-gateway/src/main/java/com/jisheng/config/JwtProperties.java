package com.jisheng.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("jwt.config")
@Data
public class JwtProperties {

    private String signKey;
    private String cookieName;
}
