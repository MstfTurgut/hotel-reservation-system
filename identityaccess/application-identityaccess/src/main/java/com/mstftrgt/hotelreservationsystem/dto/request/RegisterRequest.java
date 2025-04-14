package com.mstftrgt.hotelreservationsystem.dto.request;

import com.mstftrgt.hotelreservationsystem.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private User.Role role;
}