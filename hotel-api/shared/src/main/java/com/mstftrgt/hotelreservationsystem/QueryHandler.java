package com.mstftrgt.hotelreservationsystem;

public interface QueryHandler<Q extends Query, R> {
    R handle(Q query);
}
