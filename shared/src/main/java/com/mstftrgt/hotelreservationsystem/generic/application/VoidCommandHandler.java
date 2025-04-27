package com.mstftrgt.hotelreservationsystem.generic.application;

public interface VoidCommandHandler <C extends Command> {
    void handle(C command);
}
