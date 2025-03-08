package com.mstftrgt.hotelreservationsystem.command.user.authenticate;

import com.mstftrgt.hotelreservationsystem.CommandHandlerWithResult;
import com.mstftrgt.hotelreservationsystem.identityaccess.model.User;
import com.mstftrgt.hotelreservationsystem.identityaccess.port.PasswordVerifierPort;
import com.mstftrgt.hotelreservationsystem.identityaccess.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticateUserCommandHandler implements CommandHandlerWithResult<AuthenticateUserCommand, AuthenticationResult> {

    private final UserRepository userRepository;
    private final PasswordVerifierPort passwordVerifierPort;

    @Override
    public AuthenticationResult handle(AuthenticateUserCommand command) {

        Optional<User> userOptional = userRepository.findByEmail(command.getEmail());

        if (userOptional.isEmpty()) {
            return new AuthenticationResult("Incorrect login or password");
        }

        User user = userOptional.get();

        if (!passwordVerifierPort.doesMatch(command.getPassword(), user.getPassword())) {
            return new AuthenticationResult("Incorrect login or password");
        }

        return new AuthenticationResult(UserDto.from(user));
    }
}
