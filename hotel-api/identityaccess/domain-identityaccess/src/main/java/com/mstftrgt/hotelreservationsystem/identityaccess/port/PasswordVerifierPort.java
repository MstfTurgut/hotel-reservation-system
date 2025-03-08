package com.mstftrgt.hotelreservationsystem.identityaccess.port;


public interface PasswordVerifierPort {

    boolean doesMatch(String requestPassword, String hashedPassword);
}


/*

import org.mindrot.jbcrypt.BCrypt;

public class PasswordManager implements PasswordVerifierPort {

public boolean verifyPassword(String requestPassword, String hashedPassword) {
    try {
        return BCrypt.checkpw(requestPassword, hashedPassword);
    } catch (IllegalArgumentException e) {
        // This happens if the hash is invalid
        return false;
    }
}
}

 */