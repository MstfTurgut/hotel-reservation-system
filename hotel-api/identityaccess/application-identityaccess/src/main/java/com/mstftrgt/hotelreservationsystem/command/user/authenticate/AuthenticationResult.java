package com.mstftrgt.hotelreservationsystem.command.user.authenticate;

import lombok.Getter;

@Getter
public class AuthenticationResult {

    private UserDto user;
    private final boolean isAuthenticated;
    private String authenticationError;

    public AuthenticationResult(String authenticationError) {
        isAuthenticated = false;
        this.authenticationError = authenticationError;
    }

    public AuthenticationResult(UserDto user) {
        isAuthenticated = true;
        this.user = user;
    }

}
