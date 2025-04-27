package com.mstftrgt.hotelreservationsystem.generic.application;


public interface CommandBus {
    <C extends Command> void dispatch(C command);
    <C extends Command, R> R dispatchAndReturn(C command);
}