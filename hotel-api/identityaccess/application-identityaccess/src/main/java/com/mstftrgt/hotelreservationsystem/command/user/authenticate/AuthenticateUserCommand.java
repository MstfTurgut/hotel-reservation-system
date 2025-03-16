package com.mstftrgt.hotelreservationsystem.command.user.authenticate;

import com.mstftrgt.hotelreservationsystem.Command;
import lombok.Builder;
import lombok.Data;

@Builder
public record AuthenticateUserCommand(String email, String password) implements Command {
}
