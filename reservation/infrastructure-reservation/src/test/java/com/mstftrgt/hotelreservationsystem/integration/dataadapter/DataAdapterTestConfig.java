package com.mstftrgt.hotelreservationsystem.integration.dataadapter;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static org.mockito.Mockito.mock;

@Configuration
@Profile("dataAdapterIntegrationTest")
@ComponentScan(
        basePackages = {"com.mstftrgt.hotelreservationsystem"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*CommandHandler$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*QueryHandler$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*EventHandler$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*FacadeImpl$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Service$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*SecurityConfig$")
        }
)
@EnableJpaRepositories(basePackages = "com.mstftrgt.hotelreservationsystem.persistence.repository")
@EntityScan(basePackages = "com.mstftrgt.hotelreservationsystem.persistence.entity")
public class DataAdapterTestConfig {
}
