package com.lab.config;

import com.lab.repositories.CommentRepository;
import com.lab.repositories.DBCommentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@Configuration
@ComponentScan(basePackages = {
        "com.lab.services",
        "com.lab.repositories",
        "com.lab.proxies"
    })
public class AppConfig {

    @Bean
    public CommentRepository commentRepository() {
        return new DBCommentRepository();   // khai bao tuong minh
    }
}
