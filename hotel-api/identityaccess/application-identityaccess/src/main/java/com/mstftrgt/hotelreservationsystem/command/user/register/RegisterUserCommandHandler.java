package com.mstftrgt.hotelreservationsystem.command.user.register;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.identityaccess.model.UserCreate;
import com.mstftrgt.hotelreservationsystem.identityaccess.repository.UserRepository;
import com.mstftrgt.hotelreservationsystem.identityaccess.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserCommandHandler implements CommandHandler<RegisterUserCommand> {

    private final UserRepository userRepository;

    @Override
    public void handle(RegisterUserCommand command) {
        userRepository.save(buildUserCreate(command));
    }

    private UserCreate buildUserCreate(RegisterUserCommand command) {
        return UserCreate.builder()
                .email(command.getEmail())
                .password(command.getPassword())
                .role(UserRole.GUEST)
                .build();
    }
}
