package com.mstftrgt.hotelreservationsystem.integration.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("controllerIntegrationTest")
@ComponentScan(
        basePackages = "com.mstftrgt.hotelreservationsystem",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*(?<!Fake)CommandHandler$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*(?<!Fake)QueryHandler$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*EventHandler$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*FacadeImpl$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Adapter$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Service$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*SecurityConfig$"),
        }

)
public class ControllerTestConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
