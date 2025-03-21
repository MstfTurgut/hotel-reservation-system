package com.mstftrgt.hotelreservationsystem.readmodel;

import com.mstftrgt.hotelreservationsystem.identityaccess.model.User;
import lombok.Builder;
import java.util.UUID;

@Builder
public record UserReadModel (
        UUID id,
        String email,
        String password,
        String userRole)  {

    public static UserReadModel from(User user) {
        return UserReadModel.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .userRole(user.getUserRole().toString())
                .build();
    }

}