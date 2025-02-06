package com.mstftrgt.hotelreservationsystem.authentication.port;

import com.mstftrgt.hotelreservationsystem.authentication.model.User;

public interface UserPort {

    User findByEmail(String email);

    void save(User user);

    void login(String email, String password);

}
