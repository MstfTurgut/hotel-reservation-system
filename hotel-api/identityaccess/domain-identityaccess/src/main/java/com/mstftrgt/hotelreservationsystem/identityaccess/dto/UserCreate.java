package com.mstftrgt.hotelreservationsystem.identityaccess.dto;

import com.mstftrgt.hotelreservationsystem.identityaccess.model.UserRole;
import lombok.Builder;

@Builder
public record UserCreate(
        String email,
        String password,
        UserRole role
) {
}
