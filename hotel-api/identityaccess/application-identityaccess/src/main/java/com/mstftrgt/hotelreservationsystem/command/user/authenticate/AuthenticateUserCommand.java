package com.mstftrgt.hotelreservationsystem.command.user.authenticate;

import com.mstftrgt.hotelreservationsystem.Command;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticateUserCommand implements Command {
    private final String email;
    private final String password;
}
