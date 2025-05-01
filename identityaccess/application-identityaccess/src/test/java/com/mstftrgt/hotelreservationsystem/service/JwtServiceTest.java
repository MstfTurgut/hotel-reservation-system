package com.mstftrgt.hotelreservationsystem.service;

import com.mstftrgt.hotelreservationsystem.dto.RoleDto;
import com.mstftrgt.hotelreservationsystem.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    private JwtService jwtService;
    private UserDetails userDetails;
    private String token;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();

        // Using reflection to set private fields
        ReflectionTestUtils.setField(jwtService, "secretKey", "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970");
        ReflectionTestUtils.setField(jwtService, "jwtExpiration", 86400000L); // 1 day

        userDetails = User.builder()
                .id(UUID.randomUUID())
                .email("test@example.com")
                .password("password")
                .role(RoleDto.USER)
                .build();

        token = jwtService.generateToken(userDetails);
    }

    @Test
    void extractUsername_ValidToken_ReturnsUsername() {
        // Act
        String username = jwtService.extractUsername(token);

        // Assert
        assertEquals("test@example.com", username);
    }

    @Test
    void generateToken_ReturnsValidToken() {
        // Act
        String token = jwtService.generateToken(userDetails);

        // Assert
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void isTokenValid_ValidToken_ReturnsTrue() {
        // Act
        boolean isValid = jwtService.isTokenValid(token, userDetails);

        // Assert
        assertTrue(isValid);
    }

    @Test
    void isTokenValid_ExpiredToken_ReturnsFalse() throws Exception {
        // Arrange
        JwtService jwtServiceWithShortExpiration = new JwtService();
        ReflectionTestUtils.setField(jwtServiceWithShortExpiration, "secretKey", "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970");
        ReflectionTestUtils.setField(jwtServiceWithShortExpiration, "jwtExpiration", 1L); // 1 millisecond

        String expiredToken = jwtServiceWithShortExpiration.generateToken(userDetails);
        Thread.sleep(10); // Wait for token to expire

        // Act
        boolean isValid = jwtServiceWithShortExpiration.isTokenValid(expiredToken, userDetails);

        // Assert
        assertFalse(isValid);
    }

    @Test
    void isTokenValid_DifferentUser_ReturnsFalse() {
        // Arrange
        UserDetails differentUser = User.builder()
                .id(UUID.randomUUID())
                .email("different@example.com")
                .password("password")
                .role(RoleDto.USER)
                .build();

        // Act
        boolean isValid = jwtService.isTokenValid(token, differentUser);

        // Assert
        assertFalse(isValid);
    }
}
