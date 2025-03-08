package com.mstftrgt.hotelreservationsystem;

public interface CommandHandlerWithResult<C extends Command, R> {
    R handle(C command);
}
