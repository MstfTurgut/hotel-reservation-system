package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.identityaccess.dto.UserCreate;
import com.mstftrgt.hotelreservationsystem.identityaccess.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTests {

    @Test
    void create_ShouldCreateUser() {
        UserCreate userCreate = UserTestDataFactory.getTestUserCreate();

        User user = User.create(userCreate);

        assertNotNull(user.getId());
        assertEquals(userCreate.email(), user.getEmail());
        assertEquals(userCreate.password(), user.getPassword());
        assertEquals(userCreate.role(), user.getUserRole());
    }
}
