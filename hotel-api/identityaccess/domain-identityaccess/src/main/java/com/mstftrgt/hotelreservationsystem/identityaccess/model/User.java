package com.mstftrgt.hotelreservationsystem.identityaccess.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long id;
    private String email;
    private String password;
    private UserRole userRole;
}
