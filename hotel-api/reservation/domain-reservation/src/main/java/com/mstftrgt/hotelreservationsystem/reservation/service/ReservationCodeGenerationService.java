package com.mstftrgt.hotelreservationsystem.reservation.service;

import java.security.SecureRandom;

public class ReservationCodeGenerationService {

    private static final String DIGITS = "0123456789";
    private static final int CODE_LENGTH = 8; // You can change this length
    private static final SecureRandom RANDOM = new SecureRandom();

    public String generateReservationCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = RANDOM.nextInt(DIGITS.length());
            code.append(DIGITS.charAt(index));
        }
        return code.toString();
    }
}