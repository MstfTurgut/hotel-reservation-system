package com.mstftrgt.hotelreservationsystem.command.user.register;

import com.mstftrgt.hotelreservationsystem.Command;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserCommand implements Command {
    private final String email;
    private final String password;
}
