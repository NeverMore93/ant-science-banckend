package com.antScience.foundation.common.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by lirui on 2017/11/9.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@PropertySource("classpath:redis.properties")
public class Redis {
    private int database;
    private String hostname;
    private int port;
    private String password;
    private int timeout;
    private String maxIdle;
    private String minIdle;
    private String maxActive;
    private String maxWait;
}
