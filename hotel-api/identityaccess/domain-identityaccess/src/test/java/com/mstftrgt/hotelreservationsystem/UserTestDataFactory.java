package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.identityaccess.dto.UserCreate;
import com.mstftrgt.hotelreservationsystem.identityaccess.model.UserRole;

public class UserTestDataFactory {

    public static UserCreate getTestUserCreate() {
        return UserCreate.builder()
                .email("testEmail")
                .password("testPassword")
                .role(UserRole.GUEST)
                .build();
    }
}
