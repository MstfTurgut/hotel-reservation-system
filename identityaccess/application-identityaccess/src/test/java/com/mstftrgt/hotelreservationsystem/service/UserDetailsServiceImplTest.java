package com.mstftrgt.hotelreservationsystem.service;

import com.mstftrgt.hotelreservationsystem.dto.RoleDto;
import com.mstftrgt.hotelreservationsystem.entity.User;
import com.mstftrgt.hotelreservationsystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void loadUserByUsername_ExistingUser_ReturnsUserDetails() {
        // Arrange
        String email = "test@example.com";
        User user = User.builder()
                .id(UUID.randomUUID())
                .email(email)
                .password("encoded-password")
                .role(RoleDto.USER)
                .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // Assert
        assertNotNull(userDetails);
        assertEquals(email, userDetails.getUsername());
        assertEquals("encoded-password", userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER")));

        verify(userRepository).findByEmail(email);
    }

    @Test
    void loadUserByUsername_NonExistingUser_ThrowsUsernameNotFoundException() {
        // Arrange
        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername(email)
        );

        assertEquals("User not found with email: " + email, exception.getMessage());
        verify(userRepository).findByEmail(email);
    }
}