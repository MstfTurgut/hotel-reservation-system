package com.mstftrgt.hotelreservationsystem.command.user.addnewadmin;

import com.mstftrgt.hotelreservationsystem.Command;
import com.mstftrgt.hotelreservationsystem.identityaccess.dto.UserCreate;
import com.mstftrgt.hotelreservationsystem.identityaccess.model.User;
import com.mstftrgt.hotelreservationsystem.identityaccess.model.UserRole;
import lombok.Builder;
import lombok.Data;

@Builder
public record AddNewAdminUserCommand(String email, String password) implements Command {

    public UserCreate toUserCreateWith(UserRole userRole) {
        return UserCreate.builder()
                .role(userRole)
                .email(email)
                .password(password)
                .build();
    }
}
