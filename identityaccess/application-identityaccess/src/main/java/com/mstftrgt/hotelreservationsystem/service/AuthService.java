package com.mstftrgt.hotelreservationsystem.service;

import com.mstftrgt.hotelreservationsystem.dto.AuthResponse;
import com.mstftrgt.hotelreservationsystem.exception.AuthenticationException;
import com.mstftrgt.hotelreservationsystem.dto.LoginRequest;
import com.mstftrgt.hotelreservationsystem.dto.RegisterRequest;
import com.mstftrgt.hotelreservationsystem.entity.User;
import com.mstftrgt.hotelreservationsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AuthenticationException("Email already in use");
        }

        User user = User.builder()
                .id(UUID.randomUUID())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);
        return createAuthResponse(user);
    }

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow();

            return createAuthResponse(user);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Invalid credentials");
        }
    }

    private AuthResponse createAuthResponse(User user) {
        String jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}