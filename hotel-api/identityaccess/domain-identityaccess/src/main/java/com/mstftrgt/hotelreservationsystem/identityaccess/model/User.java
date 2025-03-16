package com.mstftrgt.hotelreservationsystem.identityaccess.model;

import com.mstftrgt.hotelreservationsystem.domain.AggregateRoot;
import com.mstftrgt.hotelreservationsystem.identityaccess.dto.UserCreate;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class User extends AggregateRoot {
    private UUID id;
    private String email;
    private String password;
    private UserRole userRole;

    public static User create(UserCreate userCreate) {
        return User.builder()
                .id(UUID.randomUUID())
                .email(userCreate.email())
                .password(userCreate.password())
                .userRole(userCreate.role())
                .build();
    }

}
