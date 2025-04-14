package com.mstftrgt.hotelreservationsystem.cqrs;

public interface CommandHandler<C extends Command> {
    void handle(C command);
}