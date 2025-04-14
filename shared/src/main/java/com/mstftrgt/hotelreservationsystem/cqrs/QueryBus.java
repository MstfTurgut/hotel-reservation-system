package com.mstftrgt.hotelreservationsystem.cqrs;

public interface QueryBus {
    <R, Q extends Query<R>> R dispatch(Q query);
}