package com.mstftrgt.hotelreservationsystem.command.user.addnewadmin;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.identityaccess.dto.UserCreate;
import com.mstftrgt.hotelreservationsystem.identityaccess.model.User;
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
        User user = User.create(command.toUserCreateWith(UserRole.ADMIN));

        userRepository.save(user);
    }
}
