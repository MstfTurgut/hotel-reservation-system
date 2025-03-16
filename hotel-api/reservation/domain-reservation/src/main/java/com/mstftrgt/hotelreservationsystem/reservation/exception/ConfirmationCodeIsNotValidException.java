package com.mstftrgt.hotelreservationsystem.reservation.exception;

public class ConfirmationCodeIsNotValidException extends RuntimeException {
    public ConfirmationCodeIsNotValidException() {
        super("Invalid confirmation code: confirmation code is not valid");
    }
}
