package com.mstftrgt.hotelreservationsystem.identityaccess.repository;


import com.mstftrgt.hotelreservationsystem.identityaccess.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    Optional<User> findById(UUID userId);

    void save(User user);
}
