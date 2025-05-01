package com.mstftrgt.hotelreservationsystem.service;

import com.mstftrgt.hotelreservationsystem.dto.AuthResponse;
import com.mstftrgt.hotelreservationsystem.dto.LoginRequest;
import com.mstftrgt.hotelreservationsystem.dto.RegisterRequest;
import com.mstftrgt.hotelreservationsystem.dto.RoleDto;
import com.mstftrgt.hotelreservationsystem.entity.User;
import com.mstftrgt.hotelreservationsystem.exception.AuthenticationException;
import com.mstftrgt.hotelreservationsystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    @Test
    void register_NewUser_ReturnsAuthResponse() {
        // Arrange
        RegisterRequest request = RegisterRequest.builder()
                .email("test@example.com")
                .password("password")
                .role(RoleDto.USER)
                .build();

        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encoded-password");
        when(jwtService.generateToken(any(UserDetails.class))).thenReturn("jwt-token");

        // Act
        AuthResponse response = authService.register(request);

        // Assert
        assertNotNull(response);
        assertEquals("jwt-token", response.getToken());

        verify(userRepository).existsByEmail("test@example.com");
        verify(passwordEncoder).encode("password");
        verify(userRepository).save(any(User.class));
        verify(jwtService).generateToken(any(User.class));
    }

    @Test
    void register_ExistingEmail_ThrowsAuthenticationException() {
        // Arrange
        RegisterRequest request = RegisterRequest.builder()
                .email("existing@example.com")
                .password("password")
                .role(RoleDto.USER)
                .build();

        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        // Act & Assert
        AuthenticationException exception = assertThrows(
                AuthenticationException.class,
                () -> authService.register(request)
        );

        assertEquals("Email already in use", exception.getMessage());
        verify(userRepository).existsByEmail("existing@example.com");
        verifyNoMoreInteractions(userRepository, passwordEncoder, jwtService);
    }

    @Test
    void login_ValidCredentials_ReturnsAuthResponse() {
        // Arrange
        LoginRequest request = LoginRequest.builder()
                .email("test@example.com")
                .password("password")
                .build();

        User user = User.builder()
                .id(UUID.randomUUID())
                .email("test@example.com")
                .password("encoded-password")
                .role(RoleDto.USER)
                .build();

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(any(UserDetails.class))).thenReturn("jwt-token");

        // Act
        AuthResponse response = authService.login(request);

        // Assert
        assertNotNull(response);
        assertEquals("jwt-token", response.getToken());

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository).findByEmail("test@example.com");
        verify(jwtService).generateToken(user);
    }

    @Test
    void login_InvalidCredentials_ThrowsAuthenticationException() {
        // Arrange
        LoginRequest request = LoginRequest.builder()
                .email("test@example.com")
                .password("wrong-password")
                .build();

        doThrow(new BadCredentialsException("Bad credentials"))
                .when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        // Act & Assert
        AuthenticationException exception = assertThrows(
                AuthenticationException.class,
                () -> authService.login(request)
        );

        assertEquals("Invalid credentials", exception.getMessage());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verifyNoInteractions(userRepository, jwtService);
    }
}
