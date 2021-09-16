package com.example.crud_with_datajpa_thymeleaf;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Admin
 */
public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "1211";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);
    }
}
