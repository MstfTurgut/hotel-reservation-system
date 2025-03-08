package com.mstftrgt.hotelreservationsystem;

public interface Mediator {
    <R> R send(Object request);
}