package com.mstftrgt.hotelreservationsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mstftrgt.hotelreservationsystem.dto.AuthResponse;
import com.mstftrgt.hotelreservationsystem.dto.LoginRequest;
import com.mstftrgt.hotelreservationsystem.dto.RegisterRequest;
import com.mstftrgt.hotelreservationsystem.dto.RoleDto;
import com.mstftrgt.hotelreservationsystem.exception.AuthenticationException;
import com.mstftrgt.hotelreservationsystem.exception.IdentityAccessRestExceptionHandler;
import com.mstftrgt.hotelreservationsystem.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .setControllerAdvice(new IdentityAccessRestExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void register_ValidRequest_ReturnsOk() throws Exception {
        // Arrange
        RegisterRequest request = RegisterRequest.builder()
                .email("test@example.com")
                .password("password")
                .role(RoleDto.USER)
                .build();

        AuthResponse response = AuthResponse.builder()
                .token("jwt-token")
                .build();

        when(authService.register(any(RegisterRequest.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token"));

        verify(authService).register(any(RegisterRequest.class));
    }

    @Test
    void register_InvalidRequest_ReturnsBadRequest() throws Exception {
        // Arrange
        RegisterRequest request = RegisterRequest.builder()
                .email("invalid-email")
                .password("")
                .build();

        // Act & Assert
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(authService);
    }

    @Test
    void login_ValidRequest_ReturnsOk() throws Exception {
        // Arrange
        LoginRequest request = LoginRequest.builder()
                .email("test@example.com")
                .password("password")
                .build();

        AuthResponse response = AuthResponse.builder()
                .token("jwt-token")
                .build();

        when(authService.login(any(LoginRequest.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token"));

        verify(authService).login(any(LoginRequest.class));
    }

    @Test
    void login_InvalidCredentials_ReturnsUnauthorized() throws Exception {
        // Arrange
        LoginRequest request = LoginRequest.builder()
                .email("test@example.com")
                .password("wrong-password")
                .build();

        when(authService.login(any(LoginRequest.class)))
                .thenThrow(new AuthenticationException("Invalid credentials"));

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid credentials"));

        verify(authService).login(any(LoginRequest.class));
    }
}
