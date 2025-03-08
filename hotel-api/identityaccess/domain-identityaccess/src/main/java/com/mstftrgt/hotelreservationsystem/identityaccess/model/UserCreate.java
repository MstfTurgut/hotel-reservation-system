package com.mstftrgt.hotelreservationsystem.identityaccess.model;

import lombok.Builder;

@Builder
public record UserCreate(
        String email,
        String password,
        UserRole role
) {
}
