package com.mstftrgt.hotelreservationsystem.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mstftrgt.hotelreservationsystem.dto.LoginRequest;
import com.mstftrgt.hotelreservationsystem.dto.RegisterRequest;
import com.mstftrgt.hotelreservationsystem.dto.RoleDto;
import com.mstftrgt.hotelreservationsystem.entity.User;
import com.mstftrgt.hotelreservationsystem.repository.UserRepository;
import com.mstftrgt.hotelreservationsystem.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = "classpath:init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureDataJpa
class AuthenticationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockitoBean
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void register_NewUser_ReturnsToken() throws Exception {
        // Arrange
        RegisterRequest request = RegisterRequest.builder()
                .email("newuser@example.com")
                .password("password123")
                .role(RoleDto.USER)
                .build();

        when(jwtService.generateToken(any(UserDetails.class))).thenReturn("mocked-jwt-token");

        // Act & Assert
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mocked-jwt-token"));

        // Verify user was saved
        Optional<User> savedUser = userRepository.findByEmail("newuser@example.com");
        assertTrue(savedUser.isPresent());
        assertEquals(RoleDto.USER, savedUser.get().getRole());
        assertTrue(passwordEncoder.matches("password123", savedUser.get().getPassword()));
    }

    @Test
    void register_ExistingEmail_ReturnsError() throws Exception {
        // Arrange
        User existingUser = User.builder()
                .id(UUID.randomUUID())
                .email("existing@example.com")
                .password(passwordEncoder.encode("password"))
                .role(RoleDto.USER)
                .build();

        userRepository.save(existingUser);

        RegisterRequest request = RegisterRequest.builder()
                .email("existing@example.com")
                .password("newpassword")
                .role(RoleDto.ADMIN)
                .build();

        // Act & Assert
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Email already in use"));
    }

    @Test
    void login_ValidCredentials_ReturnsToken() throws Exception {
        // Arrange
        User existingUser = User.builder()
                .id(UUID.randomUUID())
                .email("user@example.com")
                .password(passwordEncoder.encode("correctpassword"))
                .role(RoleDto.USER)
                .build();

        userRepository.save(existingUser);

        LoginRequest request = LoginRequest.builder()
                .email("user@example.com")
                .password("correctpassword")
                .build();

        when(jwtService.generateToken(any(UserDetails.class))).thenReturn("mocked-jwt-token");

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mocked-jwt-token"));
    }


    @Test
    void login_InvalidCredentials_ReturnsError() throws Exception {
        // Arrange
        User existingUser = User.builder()
                .id(UUID.randomUUID())
                .email("user@example.com")
                .password(passwordEncoder.encode("correctpassword"))
                .role(RoleDto.USER)
                .build();

        userRepository.save(existingUser);

        LoginRequest request = LoginRequest.builder()
                .email("user@example.com")
                .password("wrongpassword")
                .build();

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid credentials"));
    }

    @Test
    void login_NonExistentUser_ReturnsError() throws Exception {
        // Arrange
        LoginRequest request = LoginRequest.builder()
                .email("nonexistent@example.com")
                .password("anypassword")
                .build();

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid credentials"));
    }

    @Test
    void endToEnd_RegisterAndLogin_Success() throws Exception {
        // Arrange - Setup JWT service to return real tokens
        when(jwtService.generateToken(any(UserDetails.class))).thenAnswer(invocation -> {
            UserDetails userDetails = invocation.getArgument(0);
            return "jwt-token-for-" + userDetails.getUsername();
        });

        // Step 1: Register a new user
        RegisterRequest registerRequest = RegisterRequest.builder()
                .email("newuser@example.com")
                .password("secure-password")
                .role(RoleDto.ADMIN)
                .build();

        MvcResult registerResult = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token-for-newuser@example.com"))
                .andReturn();

        // Step 2: Login with the registered user
        LoginRequest loginRequest = LoginRequest.builder()
                .email("newuser@example.com")
                .password("secure-password")
                .build();

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token-for-newuser@example.com"));

        // Verify user was saved with correct role
        Optional<User> savedUser = userRepository.findByEmail("newuser@example.com");
        assertTrue(savedUser.isPresent());
        assertEquals(RoleDto.ADMIN, savedUser.get().getRole());
    }
}