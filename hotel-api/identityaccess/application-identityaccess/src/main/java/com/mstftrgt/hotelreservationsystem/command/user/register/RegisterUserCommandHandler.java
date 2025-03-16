package com.mstftrgt.hotelreservationsystem.command.user.register;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.identityaccess.dto.UserCreate;
import com.mstftrgt.hotelreservationsystem.identityaccess.model.User;
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
        User user = User.create(command.toUserCreateWith(UserRole.GUEST));

        userRepository.save(user);
    }

}
