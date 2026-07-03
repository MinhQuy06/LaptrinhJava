package org.example.baitap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.example.baitap", "com.example.productapp"})
public class BaiTapApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaiTapApplication.class, args);
    }

}