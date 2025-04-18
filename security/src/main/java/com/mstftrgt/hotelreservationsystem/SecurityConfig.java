package com.mstftrgt.hotelreservationsystem;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final IntegrationUserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/payments/reservation/{reservationId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/reservations/{reservationId}/cancel").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/reservations/{reservationId}/check-in").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/reservations/{reservationId}/check-out").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/reservations").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/reservations/availability").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/reservations/user").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/reservations/customer").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/rooms").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/rooms/{roomId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/room-types").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/room-types/{roomTypeId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/room-types/{roomTypeId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/room-types").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/room-types/{roomTypeId}/rooms").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}