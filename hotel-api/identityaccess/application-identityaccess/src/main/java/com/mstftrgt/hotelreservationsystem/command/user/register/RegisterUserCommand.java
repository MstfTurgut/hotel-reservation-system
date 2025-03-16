package com.mstftrgt.hotelreservationsystem.command.user.register;

import com.mstftrgt.hotelreservationsystem.Command;
import com.mstftrgt.hotelreservationsystem.identityaccess.dto.UserCreate;
import com.mstftrgt.hotelreservationsystem.identityaccess.model.UserRole;
import lombok.Builder;
import lombok.Data;

@Builder
public record RegisterUserCommand(String email, String password) implements Command {

    public UserCreate toUserCreateWith(UserRole userRole) {
        return UserCreate.builder()
                .email(email)
                .password(password)
                .role(userRole)
                .build();
    }
}
