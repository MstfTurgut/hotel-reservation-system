package com.mstftrgt.hotelreservationsystem.integration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.mstftrgt.hotelreservationsystem.repository")
@EntityScan(basePackages = "com.mstftrgt.hotelreservationsystem.entity")
public class TestJpaConfig {
}