package com.mstftrgt.hotelreservationsystem.command.user.authenticate;


import com.mstftrgt.hotelreservationsystem.identityaccess.model.User;
import lombok.Builder;
import java.util.UUID;

@Builder
public record UserDto (
        UUID id,
        String email,
        String password,
        String userRole)  {

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .userRole(user.getUserRole().toString())
                .build();
    }

}
