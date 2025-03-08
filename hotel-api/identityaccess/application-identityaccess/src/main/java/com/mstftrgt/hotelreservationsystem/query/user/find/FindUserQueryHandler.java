package com.mstftrgt.hotelreservationsystem.query.user.find;

import com.mstftrgt.hotelreservationsystem.QueryHandler;
import com.mstftrgt.hotelreservationsystem.identityaccess.model.User;
import com.mstftrgt.hotelreservationsystem.identityaccess.repository.UserRepository;
import com.mstftrgt.hotelreservationsystem.readmodel.UserReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindUserQueryHandler implements QueryHandler<FindUserQuery, UserReadModel> {

    private final UserRepository userRepository;

    @Override
    public UserReadModel handle(FindUserQuery query) {
        User user = userRepository.findById(query.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return UserReadModel.from(user);
    }
}
