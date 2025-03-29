package com.mstftrgt.hotelreservationsystem.reservation.service;

import java.security.SecureRandom;

public class ConfirmationCodeGenerationService {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 8; // You can change this length
    private static final SecureRandom RANDOM = new SecureRandom();

    public String generateConfirmationCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }
}
