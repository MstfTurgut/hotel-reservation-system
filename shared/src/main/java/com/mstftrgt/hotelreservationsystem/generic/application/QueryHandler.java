package com.mstftrgt.hotelreservationsystem.generic.application;

public interface QueryHandler<Q extends Query<R>, R> {
    R handle(Q query);
}