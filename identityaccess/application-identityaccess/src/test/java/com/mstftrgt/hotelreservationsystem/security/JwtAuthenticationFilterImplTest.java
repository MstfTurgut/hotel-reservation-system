package com.mstftrgt.hotelreservationsystem.security;

import com.mstftrgt.hotelreservationsystem.dto.RoleDto;
import com.mstftrgt.hotelreservationsystem.entity.User;
import com.mstftrgt.hotelreservationsystem.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterImplTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtAuthenticationFilterImpl jwtAuthenticationFilter;

    @Test
    void doFilterInternal_NoAuthHeader_ContinuesChain() throws Exception {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn(null);

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtService, userDetailsService);
    }


    @Test
    void doFilterInternal_InvalidAuthHeader_ContinuesChain() throws Exception {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn("Invalid");

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtService, userDetailsService);
    }

    @Test
    void doFilterInternal_ValidToken_SetsAuthentication() throws Exception {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn("Bearer valid-token");
        when(jwtService.extractUsername("valid-token")).thenReturn("test@example.com");

        MockedStatic<SecurityContextHolder> securityContextHolderMock = mockStatic(SecurityContextHolder.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        securityContextHolderMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);
        when(securityContext.getAuthentication()).thenReturn(null);

        UserDetails userDetails = User.builder()
                .email("test@example.com")
                .password("password")
                .role(RoleDto.USER)
                .build();

        when(userDetailsService.loadUserByUsername("test@example.com")).thenReturn(userDetails);
        when(jwtService.isTokenValid("valid-token", userDetails)).thenReturn(true);

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(jwtService).extractUsername("valid-token");
        verify(userDetailsService).loadUserByUsername("test@example.com");
        verify(jwtService).isTokenValid("valid-token", userDetails);
        verify(securityContext).setAuthentication(any(UsernamePasswordAuthenticationToken.class));
        verify(filterChain).doFilter(request, response);

        securityContextHolderMock.close();
    }

    @Test
    void doFilterInternal_InvalidToken_DoesNotSetAuthentication() throws Exception {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn("Bearer invalid-token");
        when(jwtService.extractUsername("invalid-token")).thenReturn("test@example.com");

        MockedStatic<SecurityContextHolder> securityContextHolderMock = mockStatic(SecurityContextHolder.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        securityContextHolderMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);
        when(securityContext.getAuthentication()).thenReturn(null);

        UserDetails userDetails = User.builder()
                .email("test@example.com")
                .password("password")
                .role(RoleDto.USER)
                .build();

        when(userDetailsService.loadUserByUsername("test@example.com")).thenReturn(userDetails);
        when(jwtService.isTokenValid("invalid-token", userDetails)).thenReturn(false);

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(jwtService).extractUsername("invalid-token");
        verify(userDetailsService).loadUserByUsername("test@example.com");
        verify(jwtService).isTokenValid("invalid-token", userDetails);
        verify(securityContext, never()).setAuthentication(any(UsernamePasswordAuthenticationToken.class));
        verify(filterChain).doFilter(request, response);

        securityContextHolderMock.close();
    }
}
