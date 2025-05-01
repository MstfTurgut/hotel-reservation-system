package com.mstftrgt.hotelreservationsystem.generic.application;

public interface QueryBus {
    <R, Q extends Query<R>> R dispatchAndReturn(Q query);
}