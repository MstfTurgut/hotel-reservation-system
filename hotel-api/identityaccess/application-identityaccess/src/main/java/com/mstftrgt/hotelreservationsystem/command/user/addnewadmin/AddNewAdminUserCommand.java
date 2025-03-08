package com.mstftrgt.hotelreservationsystem.command.user.addnewadmin;

import com.mstftrgt.hotelreservationsystem.Command;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddNewAdminUserCommand implements Command {
    private final String email;
    private final String password;
}
