package com.mstftrgt.hotelreservationsystem.cqrs;

public interface CommandBus {
    <C extends Command> void dispatch(C command);
}