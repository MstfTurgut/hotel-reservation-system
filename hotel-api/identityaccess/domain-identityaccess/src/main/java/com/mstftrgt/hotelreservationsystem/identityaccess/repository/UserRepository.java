package com.mstftrgt.hotelreservationsystem.identityaccess.repository;


import com.mstftrgt.hotelreservationsystem.identityaccess.model.User;
import com.mstftrgt.hotelreservationsystem.identityaccess.model.UserCreate;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    Optional<User> findById(long userId);

    void save(UserCreate userCreate);
}
