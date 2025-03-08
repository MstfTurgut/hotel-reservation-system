package com.mstftrgt.hotelreservationsystem;


public interface CommandHandler<C extends Command> {
    void handle(C command);
}
