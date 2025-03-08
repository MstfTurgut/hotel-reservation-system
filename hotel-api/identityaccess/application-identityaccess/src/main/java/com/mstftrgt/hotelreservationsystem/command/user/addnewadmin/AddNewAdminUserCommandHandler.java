package com.mstftrgt.hotelreservationsystem.command.user.addnewadmin;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.identityaccess.model.UserCreate;
import com.mstftrgt.hotelreservationsystem.identityaccess.repository.UserRepository;
import com.mstftrgt.hotelreservationsystem.identityaccess.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddNewAdminUserCommandHandler implements CommandHandler<AddNewAdminUserCommand> {

    private final UserRepository userRepository;

    @Override
    public void handle(AddNewAdminUserCommand command) {
        userRepository.save(buildUserCreate(command));
    }

    private UserCreate buildUserCreate(AddNewAdminUserCommand command) {
        return UserCreate.builder()
                .email(command.getEmail())
                .password(command.getPassword())
                .role(UserRole.ADMIN)
                .build();
    }
}
