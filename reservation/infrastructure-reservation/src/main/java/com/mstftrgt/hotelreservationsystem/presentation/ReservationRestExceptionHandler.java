package com.mstftrgt.hotelreservationsystem.presentation;

import com.mstftrgt.hotelreservationsystem.generic.infrastructure.ErrorResponse;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ConfirmationCodeIsNotValidException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.InvalidStayDateException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.LastMinuteCancellationException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationAlreadyCancelledException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationAlreadyCheckedInException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationAlreadyCheckedOutException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationNotAvailableException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReservationRestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ErrorResponse handle(MethodArgumentNotValidException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConfirmationCodeIsNotValidException.class)
    protected ErrorResponse handle(ConfirmationCodeIsNotValidException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidStayDateException.class)
    protected ErrorResponse handle(InvalidStayDateException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LastMinuteCancellationException.class)
    protected ErrorResponse handle(LastMinuteCancellationException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReservationAlreadyCancelledException.class)
    protected ErrorResponse handle(ReservationAlreadyCancelledException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReservationAlreadyCheckedInException.class)
    protected ErrorResponse handle(ReservationAlreadyCheckedInException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReservationAlreadyCheckedOutException.class)
    protected ErrorResponse handle(ReservationAlreadyCheckedOutException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReservationNotAvailableException.class)
    protected ErrorResponse handle(ReservationNotAvailableException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReservationNotFoundException.class)
    protected ErrorResponse handle(ReservationNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}
