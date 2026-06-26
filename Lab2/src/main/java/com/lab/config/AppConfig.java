package com.lab.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@Configuration
@ComponentScan(basePackages = {
        "com.lab.services",
        "com.lab.repositories",
        "com.lab.proxies"
    })
public class AppConfig {
}
