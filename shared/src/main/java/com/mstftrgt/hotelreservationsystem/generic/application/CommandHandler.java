package com.mstftrgt.hotelreservationsystem.generic.application;

public interface CommandHandler<C extends Command, R> {
    R handle(C command);
}