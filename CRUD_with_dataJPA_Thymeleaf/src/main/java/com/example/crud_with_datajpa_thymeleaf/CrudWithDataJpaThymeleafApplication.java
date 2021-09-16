package com.example.crud_with_datajpa_thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CrudWithDataJpaThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudWithDataJpaThymeleafApplication.class, args);
    }

}
